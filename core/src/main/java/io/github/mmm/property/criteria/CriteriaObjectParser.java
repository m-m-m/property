/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.base.text.CaseHelper;
import io.github.mmm.property.criteria.impl.CriteriaAggregationImpl;
import io.github.mmm.property.criteria.impl.SimplePredicate;
import io.github.mmm.scanner.CharScannerParser;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.mmm.value.CriteriaObject;
import io.github.mmm.value.PropertyPath;
import io.github.mmm.value.SimplePath;

/**
 * {@link CharScannerParser} for {@link CriteriaObject}.<br>
 * <b>ATTENTION:</b> When {@link #parse(CharStreamScanner) parsing} a {@link PropertyPath} only a {@link SimplePath}
 * will be created and returned. So e.g. for "alias.Property" it is impossible without further context to determine the
 * bean type of "alias". Therefore higher-level parsers have to post-process such results and replace them with real
 * {@link io.github.mmm.property.Property properties}.
 *
 * @since 1.0.0
 */
public class CriteriaObjectParser implements CharScannerParser<CriteriaObject<?>> {

  private static CriteriaObjectParser INSTANCE = new CriteriaObjectParser();

  private static final CharFilter OP_FILTER = c -> ((c >= '0') && (c <= '9')) || ((c >= 'a') && (c <= 'z'))
      || ((c >= 'A') && (c <= 'Z')) || (c == '+') || (c == '-') || (c == '<') || (c == '>') || (c == '=');

  private static final CharFilter NUMER_FILTER = c -> ((c >= '0') && (c <= '9')) || (c == 'e') || (c == 'E')
      || (c == '+') || (c == '-') || (c == '.');

  @Override
  public CriteriaObject<?> parse(CharStreamScanner scanner) {

    return parse(scanner, SimplePathParser.INSTANCE);
  }

  /**
   * @param scanner the {@link CharStreamScanner} to read from.
   * @param pathParser the {@link PropertyPathParser}.
   * @return the parsed {@link CriteriaObject}.
   */
  public CriteriaObject<?> parse(CharStreamScanner scanner, PropertyPathParser pathParser) {

    int cp = scanner.peek();
    if (cp == '(') {
      scanner.next();
      scanner.skipWhile(' ');
      CriteriaObject<?> expression = parse(scanner);
      scanner.skipWhile(' ');
      if (!scanner.expectOne(')')) {
        throw new IllegalArgumentException("Missing ')'.");
      }
      return expression;
    }
    return parse(scanner, pathParser, null, null);
  }

  private CriteriaObject<?> parse(CharStreamScanner scanner, PropertyPathParser pathParser,
      CriteriaObject<?> expression, CriteriaOperator operator) {

    List<CriteriaObject<?>> expressions = null;
    if (expression != null) {
      assert (operator != null);
      expressions = new ArrayList<>();
      expressions.add(expression);
    }
    do {
      if (scanner.peek() == '(') {
        expression = parse(scanner);
      } else {
        expression = parsePredicate(scanner, pathParser);
      }
      if (expressions != null) {
        expressions.add(expression);
      }
      int spaces = scanner.skipWhile(' ');
      int cp = scanner.peek();
      if ((cp == ',') || (cp == ')') || (cp == CharStreamScanner.EOS)) {
        break;
      } else if ((cp == 'o') || (cp == 'O')) {
        if (scanner.expect("ORDER BY ", true, true)) {
          break;
        }
      } else if ((cp == 'g') || (cp == 'G')) {
        if (scanner.expect("GROUP BY ", true, true)) {
          break;
        }
      } else if ((cp == 'h') || (cp == 'H')) {
        if (scanner.expect("HAVING ", true, true)) {
          break;
        }
      }
      CriteriaOperator newOp = parseOperator(scanner);
      spaces = scanner.skipWhile(' ');
      assert (spaces > 0);
      if (expressions == null) {
        expressions = new ArrayList<>();
        expressions.add(expression);
      }
      if (operator == null) {
        operator = newOp;
      } else if (newOp != operator) {
        if (newOp.getPriority() > operator.getPriority()) {
          expressions.remove(expressions.size() - 1);
          expression = parse(scanner, pathParser, expression, newOp);
        } else {
          expressions.add(expression);
          expression = operator.expression(expressions);
          expressions.clear();
        }
      }
    } while (true);
    if (expressions != null) {
      assert (operator != null);
      expression = operator.expression(expressions);
    }
    return expression;
  }

  /**
   * Parses a simple selection what is a {@link Literal}, a {@link PropertyPath}, or a {@link CriteriaAggregation}.
   *
   * @param scanner the {@link CharStreamScanner} to read from.
   * @param pathParser the {@link PropertyPathParser}.
   * @return the parsed {@link CriteriaObject}.
   */
  public CriteriaObject<?> parseSelection(CharStreamScanner scanner, PropertyPathParser pathParser) {

    Literal<?> literal = parseLiteral(scanner);
    if (literal != null) {
      return literal;
    }
    String segment = PropertyPathParser.parseSegment(scanner);
    CriteriaOperator operator = parseOperator(segment);
    if (operator != null) {
      assert !operator.isInfix() : operator.toString();
      scanner.skipWhile(' ');
      scanner.requireOne('(');
      CriteriaExpression<?> result;
      if (operator instanceof CriteriaAggregationOperator) {
        CriteriaObject<?> arg = parseSelection(scanner, pathParser);
        CriteriaAggregationOperator aggOp = (CriteriaAggregationOperator) operator;
        result = new CriteriaAggregationImpl<>(aggOp, arg);
      } else if (operator == PredicateOperator.NOT) {
        CriteriaObject<?> arg = parse(scanner);
        if (arg instanceof CriteriaPredicate) {
          result = ((CriteriaPredicate) arg).not();
        } else {
          // actually invalid? "NOT(1+2)"
          result = new SimplePredicate(arg, PredicateOperator.NOT, null);
        }
      } else {
        throw new IllegalStateException("Invalid operator at this place: " + operator);
      }
      scanner.skipWhile(' ');
      scanner.requireOne(')');
      return result;
    } else if (scanner.expectOne('.')) {
      PropertyPath<?> path = pathParser.parse(scanner, segment);
      return path;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Tries to read a {@link Literal} from the given {@link CharStreamScanner}.
   *
   * @param scanner the {@link CharStreamScanner} to read from.
   * @return the parsed {@link Literal} or {@code null} if {@link CharStreamScanner} is not pointing to a
   *         {@link Literal}. In the latter case state of {@code scanner} remains unchanged.
   */
  public Literal<?> parseLiteral(CharStreamScanner scanner) {

    int cp = scanner.peek();
    if (cp == '\'') {
      scanner.next();
      String string = scanner.readUntil(cp, false, cp);
      return Literal.of(string);
    } else if (((cp == 't') || (cp == 'T')) && scanner.expect("TRUE", true)) {
      return BooleanLiteral.TRUE;
    } else if (((cp == 'f') || (cp == 'F')) && scanner.expect("FALSE", true)) {
      return BooleanLiteral.FALSE;
    } else if ((cp == '+') || (cp == '-') || CharFilter.LATIN_DIGIT.accept(cp)) {
      String num = scanner.readWhile(NUMER_FILTER);
      int len = num.length();
      if ((cp == '+') || (cp == '-')) {
        len--;
      }
      Number number;
      if ((num.indexOf('.') >= 0) || (num.indexOf('e') > 0) || (num.indexOf('E') > 0)) {
        number = new BigDecimal(num);
      } else if (len <= 10) {
        number = Integer.valueOf(num);
      } else if (len <= 19) {
        number = Long.valueOf(num);
      } else {
        number = new BigInteger(num);
      }
      return Literal.of(number);
    }
    return null;
  }

  private PredicateOperator parsePredicateOperator(CharStreamScanner scanner) {

    CriteriaOperator op = parseOperator(scanner);
    if (op instanceof PredicateOperator) {
      return (PredicateOperator) op;
    }
    throw new IllegalArgumentException("Expected predicate operator but found '" + op + "'.");
  }

  private CriteriaOperator parseOperator(CharStreamScanner scanner) {

    String op = scanner.readWhile(OP_FILTER);
    CriteriaOperator operator = parseOperator(op);
    if (operator == null) {
      throw new IllegalArgumentException("Invalid operator '" + op + "'.");
    }
    return operator;
  }

  private CriteriaOperator parseOperator(String token) {

    return CriteriaOperator.of(CaseHelper.toUpperCase(token));
  }

  /**
   * @param scanner the {@link CharStreamScanner} to read from.
   * @param pathParser the {@link PropertyPathParser}.
   * @return the parsed {@link CriteriaPredicate}.
   */
  public CriteriaPredicate parsePredicate(CharStreamScanner scanner, PropertyPathParser pathParser) {

    CriteriaObject<?> arg1 = parseSelection(scanner, pathParser);
    scanner.skipWhile(' ');
    PredicateOperator operator = parsePredicateOperator(scanner);
    scanner.skipWhile(' ');
    CriteriaObject<?> arg2 = parseSelection(scanner, pathParser);
    return new SimplePredicate(arg1, operator, arg2);
  }

  /**
   * Parses a simple selection what is a {@link Literal}, a {@link PropertyPath}, or a {@link CriteriaAggregation}.
   *
   * @param scanner the {@link CharStreamScanner} to read from.
   * @return the parsed {@link CriteriaObject}.
   */
  public CriteriaObject<?> parseSelection(CharStreamScanner scanner) {

    return parseSelection(scanner, SimplePathParser.INSTANCE);
  }

  /**
   * @return the singleton instance of this class.
   */
  public static CriteriaObjectParser get() {

    return INSTANCE;
  }

}

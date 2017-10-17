/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.impl.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.sf.mmm.property.api.factory.PropertyFactoryManager;
import net.sf.mmm.property.impl.factory.PropertyFactoryManagerImpl;
import net.sf.mmm.util.json.impl.spring.UtilJsonSpringConfig;
import net.sf.mmm.util.reflect.impl.spring.UtilReflectSpringConfig;

/**
 * This is the Spring {@link Configuration} for {@link net.sf.mmm.property}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Configuration
@ComponentScan(basePackageClasses = PropertyFactoryManagerImpl.class)
@Import({ UtilReflectSpringConfig.class, UtilJsonSpringConfig.class })
@SuppressWarnings("javadoc")
public class UtilPropertySpringConfig {

  @Bean
  public PropertyFactoryManager propertyFactoryManager() {

    return new PropertyFactoryManagerImpl();
  }

}

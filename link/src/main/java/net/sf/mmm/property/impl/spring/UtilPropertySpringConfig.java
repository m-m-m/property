/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.impl.spring;

import net.sf.mmm.property.factory.PropertyFactoryManager;
import net.sf.mmm.property.factory.PropertyFactoryManagerImpl;

/**
 * This is the Spring {@link Configuration} for {@link net.sf.mmm.property}.
 *
 * @author hohwille
 * @since 1.0.0
 */
// @Configuration
// @ComponentScan(basePackageClasses = PropertyFactoryManagerImpl.class)
// @Import({ UtilReflectSpringConfig.class, UtilJsonSpringConfig.class })
@SuppressWarnings("javadoc")
public class UtilPropertySpringConfig {

  // @Bean
  public PropertyFactoryManager propertyFactoryManager() {

    return new PropertyFactoryManagerImpl();
  }

}

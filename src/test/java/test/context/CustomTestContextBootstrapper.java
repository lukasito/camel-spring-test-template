package test.context;

import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.support.DefaultTestContextBootstrapper;

public class CustomTestContextBootstrapper extends DefaultTestContextBootstrapper {

  @Override
  protected Class<? extends ContextLoader> getDefaultContextLoaderClass(final Class<?> testClass) {
    return CamelSpringDelegatingTestContextLoader.class;
  }
}
package test;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

import test.context.CustomTestContextBootstrapper;
import test.context.DefaultContext;

@ContextConfiguration(classes = DefaultContext.class)
@MockEndpointsAndSkip("file:*")
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CustomTestContextBootstrapper.class)
public class AnnotationContextRouteTest {

  @Autowired
  private CamelContext context;

  @Produce(uri = "direct:something")
  private ProducerTemplate template;

  @EndpointInject(uri = "mock://file:out")
  private MockEndpoint mock;

  @Test
  public void testAnnotationContextRoute() throws Exception {
    mock.expectedBodiesReceived("Hello world Lukasito!");

    template.sendBody("Lukasito");

    MockEndpoint.assertIsSatisfied(context);
  }
}

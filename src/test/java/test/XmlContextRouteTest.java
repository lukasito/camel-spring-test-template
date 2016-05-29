package test;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:application-test-context.xml")
@MockEndpoints("file:*")
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
public class XmlContextRouteTest {

  @Autowired
  private CamelContext context;

  @Produce(uri = "direct:something")
  private ProducerTemplate template;

  @EndpointInject(uri = "mock://file:C:/out")
  private MockEndpoint mock;

  @Test
  public void testXmlContextRoute() throws Exception {
    mock.expectedBodiesReceived("Hello world Lukasito!");

    template.sendBody("Lukasito");

    MockEndpoint.assertIsSatisfied(context);
  }
}

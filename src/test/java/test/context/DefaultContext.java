package test.context;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultContext extends SingleRouteCamelConfiguration {

  @Override
  public RouteBuilder route() {
    return new RouteBuilder() {

      @Override
      public void configure() throws Exception {
        from("direct:something")
          .setBody(simple("helloworld ${body}"))
          .to("file://C:/out");
      }
    };
  }
}

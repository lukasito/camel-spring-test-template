# camel-spring-test-template
Gradle project about how to test your camel routes with spring application context

## Test uses these annotations

    @ContextConfiguration("classpath:application-test-context.xml")
    @MockEndpoints("file:*")
    @RunWith(CamelSpringJUnit4ClassRunner.class)
    @BootstrapWith(CamelTestContextBootstrapper.class)
    public class CamelRouteBuilderTest {
    
      @Autowired
      private CamelContext context;
    
      @Produce(uri = "...")
      private ProducerTemplate template;
    
      @EndpointInject(uri = "mock:...")
      private MockEndpoint mock;
    
      @Test
      public void test() throws Exception {
        ...
      }
    }


where **application-test-context.xml** has nothing but

    <context:component-scan base-package="test.context" />
    
which practically refers to spring java @Configuration under **test.context** package 

    @Configuration
    public class DefaultContext extends SingleRouteCamelConfiguration {
    
      @Override
      public RouteBuilder route() {
        return new RouteBuilder() {
          ...
        };
      }
    }
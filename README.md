# camel-spring-test-template
Since <http://camel.apache.org/spring-testing.html> do not provide a full example on how to create camel-spring tests with annotation configuration, I decided to do it on my own. I was struggling with camel-spring tests multiple times and always forgot how to write those tests.
Its basically gradle project about how to test your camel routes with spring application context.

### There are 2 kinds of tests:

1.  using spring **xml** test application context
2.  using **annotation** config. application context

The main difference is in what loader has been used. In **1-st** case I used the common `CamelTestContextBootstrapper` as documented on camel site. In the **2-nd** case I've created custom `CustomTestContextBootstrapper` which uses already implemented `CamelSpringDelegatingTestContextLoader` which supports parameter _classes_ in `@ContextConfiguration` annotation.

### Why @BootstrapWith?
Still, you can use _loader_ parameter in `@ContextConfiguration` annotation with eather `CamelSpringDelegatingTestContextLoader` for annotation config support or `CamelSpringTestContextLoader` for xml _locations_ support. But the main reason to use it is that you can create your AbstractRouteTest and use this annotation there and your extended test doesn't need to specify loader in `@ContextConfiguration` annotation all the time (if your tests are using different contexts). 

#### CamelSpringTestContextLoader vs CamelSpringDelegatingTestContextLoader
Besides that first one supports only xml configurations and second one supports both, they are pretty much the same. The `CamelSpringTestContextLoader` supports also `@LazyLoadTypeConverters`, but this functionality is already deprecated. I would go with the second one. I've also noticed, that `CamelSpringDelegatingTestContextLoader` do not have it's own bootstrapper, so you need to create it (as I did) or just use _loader_ parameter. Note, that `@BootstrapWith` is available in spring since version `4.1`.

### What annotations can I use with my camel test?
All as described [here](http://camel.apache.org/spring-testing.html#SpringTesting-CamelEnhancedSpringTest "Camel Enhanced Spring Test")

### Using @MockEndpoints and @MockEndpointsAndSkip
This annotation(s) can actually save you from weaving your routes. Maybe it's self-explanatory, but I can explain:

If your endpoint is `file://out?fileName=asdf` mocked endpoint will looks like this `mock://file:out`. You can mock it with simple `file:*` regexp. Also, it doesn't matter if you use also the `mock://...` or just `mock:...`, the `InterceptSendToMockEndpointStrategy` used in loader will do the normalization for you. Mind, that this will create a proxy over this endpint and **it will actually call the original one!** If you don't want it to happen, use `@MockEndpointsAndSkip`

### TODO
*   weaving with `@UseAdviceWith`

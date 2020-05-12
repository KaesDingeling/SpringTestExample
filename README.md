# SpringTestExample

There are 2 test examples in this project:

- Profiles [Source](https://github.com/KaesDingeling/SpringTestExample/tree/master/src/main/java/de/kaesdingeling/spring/test/example) | [Tests](https://github.com/KaesDingeling/SpringTestExample/tree/master/src/test/java/de/kaesdingeling/spring/test/example/test)
- REST-Tests [Source](https://github.com/KaesDingeling/SpringTestExample/tree/master/src/main/java/de/kaesdingeling/springtestexample) | [Tests](https://github.com/KaesDingeling/SpringTestExample/tree/master/src/test/java/de/kaesdingeling/springtestexample/test)


## Tests with Profiles
To be able to test only parts of the application without loading parts of the application which are not necessary for testing.

With the annotation @JunitTestWithWebServer the Tomcat is loaded during testing.
With @JunitTestWithoutWebServer there is no server to start.

The Junit tests are controlled by profiles, which allows to load configurations, services and components only if the profile is active.
It is possible to assign several profiles to a test.

### References
- https://spring.io/blog/2011/06/21/spring-3-1-m2-testing-with-configuration-classes-and-profiles
- https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#spring-testing-annotation-bootstrapwith


## REST-Tests
Tests are shown how to test a REST client under Spring with the Mockserver.

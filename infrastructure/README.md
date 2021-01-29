## Infrastructure Layer

This subproject manages the infrastructure layer.

In this layer, we code classes that implement the `Repository` interface in the domain layer.

Spring framework (DI container) will create instances of classes in this layer.
So usually we do not need to use `public` scope in this module.
Just annotate classes with [`@Repository`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html) or other spring annotations then Spring framework will find it automatically.

### Dependency

This layer depends on the domain layer via the [`api` configuration](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation).
Because classes in this layer needs domain objects as its interface, arguments, and returned value.

This layer also depends on infrastructure specific modules such as JDBC driver, [`spring-data`](https://spring.io/projects/spring-data), etc.
This dependency uses the [`implementation` configuration](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation) so it won't be disclosed to the user of this layer.

## Domain Layer

This subproject manages the domain layer.

In this layer, we code domain model (value object and entity), spec and repository interface.
This project uses [Immutables](http://immutables.github.io/) to generate immutable domain models, refer its document for detail.

For spec and domain service, it's suggested to have both of interface and implementation. It makes tests in other subprojects simple and independent.
To make implementations autowired-able, annotate your classes by [`@Service`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html) or other spring annotations.

### Dependency

This layer has no dependency. It uses the spring annotation and Immutables, but these annotations are in `compileOnly` scope, so requires nothing in runtime.

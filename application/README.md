## Application Layer

This submodule manages the application layer.

In this layer, we code classes that represents use cases of each domain.

### Dependency

This layer depends on the domain layer and infrastructure layer via the [`implementation` configuration](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation).
This means that, classes in this module should not disclose domain objects as type of parameter, returned value, and its interfaces.

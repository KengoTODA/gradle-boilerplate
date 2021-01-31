## Presentation Layer

This subproject manages the presentation layer.

In this layer, we code classes that are responsible to map the followings:

1. output of application layer to servlet response
2. servlet request to input for application layer

If your infrastructure handles transaction, this layer also manages the followings:

1. when to begin the transaction
2. when to commit/rollback the transaction

## Dependency

This subproject depends on the application layer.
The application layer does not export domain objects, so this presentation layer cannot access them.

package jp.skypencil;

import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * The immutable DTO representing task to handle.
 *
 * Will be returned from domain objects to caller, generally Controllers.
 */
final class TaskModel {
    final UUID id;
    final String subject;
    final boolean done;

    TaskModel(@NonNull Task task) {
        this.id = task.getId().getUuid();
        this.subject = task.getSubject();
        this.done = task.isDone();
    }
}

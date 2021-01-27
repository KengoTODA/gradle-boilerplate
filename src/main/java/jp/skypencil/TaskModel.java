package jp.skypencil;

import java.util.UUID;
import org.springframework.lang.NonNull;

/**
 * The immutable DTO representing task to handle.
 *
 * <p>Will be returned from domain objects to caller, generally Controllers.
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

  public UUID getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public boolean isDone() {
    return done;
  }
}

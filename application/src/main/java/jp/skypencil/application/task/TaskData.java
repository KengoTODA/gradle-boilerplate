package jp.skypencil.application.task;

import java.util.UUID;
import jp.skypencil.domain.model.Task;
import org.springframework.lang.NonNull;

/**
 * The immutable DTO representing task to handle.
 *
 * <p>Will be returned from ApplicationService to caller, generally Controllers.
 */
public final class TaskData {
  final UUID id;
  final String subject;
  final boolean done;

  public TaskData(UUID id, String subject, boolean done) {
    this.id = id;
    this.subject = subject;
    this.done = done;
  }

  public TaskData(@NonNull Task task) {
    this.id = task.id().uuid();
    this.subject = task.subject();
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

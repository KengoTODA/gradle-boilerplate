package jp.skypencil.domain.model;

import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.lang.NonNull;

public interface TaskRepository {
  void save(Task task);

  Optional<Task> find(TaskId id);

  boolean exists(@NonNull String subject);

  Stream<Task> list();
}

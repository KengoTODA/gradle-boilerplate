package jp.skypencil.domain.model;

import java.util.Optional;
import java.util.stream.Stream;

public interface TaskRepository {
  void save(Task task);

  Optional<Task> find(TaskId id);

  Stream<Task> list();
}

package jp.skypencil.infrastructure.onmemory.task;

import java.util.*;
import java.util.stream.Stream;
import jp.skypencil.domain.model.Task;
import jp.skypencil.domain.model.TaskId;
import jp.skypencil.domain.model.TaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OnMemoryTaskRepository implements TaskRepository {
  private final Map<TaskId, Task> store = Collections.synchronizedMap(new LinkedHashMap<>());

  @Override
  public void save(Task task) {
    store.put(task.id(), task);
  }

  @Override
  public Optional<Task> find(TaskId id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public boolean exists(String subject) {
    Objects.requireNonNull(subject);
    return store.values().stream().anyMatch(task -> task.subject().equals(subject));
  }

  @Override
  public Stream<Task> list() {
    return store.values().stream();
  }

  /** Clean tasks for unit tests */
  public void clear() {
    store.clear();
  }
}

package jp.skypencil;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
class TaskRepository {
  private final Map<TaskId, Task> store = Collections.synchronizedMap(new LinkedHashMap<>());

  void save(Task task) {
    store.put(task.getId(), task);
  }

  Optional<Task> find(TaskId id) {
    return Optional.ofNullable(store.get(id));
  }

  Stream<Task> list() {
    return store.values().stream();
  }
}

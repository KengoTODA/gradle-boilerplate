package jp.skypencil;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Repository
class TaskRepository {
    private final Map<TaskId, Task> store = new ConcurrentHashMap<>();

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

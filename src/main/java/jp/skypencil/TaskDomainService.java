package jp.skypencil;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The DomainService handling task related operations that should not be behaviour of the {@link
 * Task} class. It returns not domain object {@link Task} but DTO {@link TaskModel}, to make callers
 * unable to call other methods provided for domain objects.
 */
@Service
class TaskDomainService {
  private final TaskRepository repository;

  @Autowired
  TaskDomainService(TaskRepository repository) {
    this.repository = repository;
  }

  Optional<TaskModel> find(UUID id) {
    return repository.find(new TaskId(id)).map(TaskModel::new);
  }

  Stream<TaskModel> list() {
    return repository.list().map(TaskModel::new);
  }

  TaskModel create(String subject) {
    Task task = new Task(TaskId.create(), subject, false);
    repository.save(task);
    return new TaskModel(task);
  }
}

package jp.skypencil.application.task;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import jp.skypencil.domain.model.Task;
import jp.skypencil.domain.model.TaskId;
import jp.skypencil.domain.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ApplicationService handling task related operations that should not be behaviour of the
 * {@link Task} class. It returns not domain object {@link Task} but DTO {@link TaskData}, to make
 * callers unable to call other methods provided for domain objects.
 */
@Service
public class TaskApplicationService {
  private final TaskRepository repository;

  @Autowired
  TaskApplicationService(TaskRepository repository) {
    this.repository = repository;
  }

  public TaskData create(String subject) {
    Task task = new Task(TaskId.create(), subject, false);
    repository.save(task);
    return new TaskData(task);
  }

  public Optional<TaskData> find(UUID id) {
    return repository.find(new TaskId(id)).map(TaskData::new);
  }

  public Stream<TaskData> listAll() {
    return repository.list().map(TaskData::new);
  }

  public Stream<TaskData> listToDo() {
    return repository.list().filter(((Predicate<Task>) Task::isDone).negate()).map(TaskData::new);
  }
}

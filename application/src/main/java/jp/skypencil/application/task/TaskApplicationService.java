package jp.skypencil.application.task;

import java.util.function.Predicate;
import java.util.stream.Stream;
import jp.skypencil.domain.model.Task;
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

  public Stream<TaskData> listToDo() {
    return repository.list().filter(((Predicate<Task>) Task::isDone).negate()).map(TaskData::new);
  }
}

package jp.skypencil.application.task;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import jp.skypencil.domain.model.ImmutableTask;
import jp.skypencil.domain.model.ImmutableTaskId;
import jp.skypencil.domain.model.Task;
import jp.skypencil.domain.model.TaskId;
import jp.skypencil.domain.model.TaskRepository;
import jp.skypencil.domain.service.TaskDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ApplicationService handling task related operations that should not be behaviour of the
 * {@link Task} class. It returns not domain object {@link Task} but DTO {@link TaskData}, to make
 * callers unable to call other methods provided for domain objects.
 */
@Service
class DefaultTaskApplicationService implements TaskApplicationService {
  private final TaskRepository repository;
  private final TaskDomainService domainService;

  @Autowired
  public DefaultTaskApplicationService(TaskDomainService domainService, TaskRepository repository) {
    this.domainService = domainService;
    this.repository = repository;
  }

  @Override
  public TaskData create(String subject) {
    if (domainService.duplicates(subject)) {
      throw new TaskDuplicationException();
    }

    Task task = ImmutableTask.builder().id(TaskId.create()).subject(subject).isDone(false).build();
    repository.save(task);
    return new TaskData(task);
  }

  @Override
  public Optional<TaskData> find(UUID id) {
    return repository.find(ImmutableTaskId.builder().uuid(id).build()).map(TaskData::new);
  }

  @Override
  public Stream<TaskData> listAll() {
    return repository.list().map(TaskData::new);
  }

  @Override
  public Stream<TaskData> listToDo() {
    return repository.list().filter(((Predicate<Task>) Task::isDone).negate()).map(TaskData::new);
  }
}

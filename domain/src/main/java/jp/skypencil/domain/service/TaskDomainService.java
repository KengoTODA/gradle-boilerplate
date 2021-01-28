package jp.skypencil.domain.service;

import jp.skypencil.domain.model.Task;
import jp.skypencil.domain.model.TaskId;
import jp.skypencil.domain.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The DomainService handling task related operations that should not be behaviour of the {@link
 * Task} class. It returns not domain object {@link Task} but DTO {@link TaskModel}, to make callers
 * unable to call other methods provided for domain objects.
 */
@Service
public class TaskDomainService {
  private final TaskRepository repository;

  @Autowired
  TaskDomainService(TaskRepository repository) {
    this.repository = repository;
  }

  public Task create(String subject) {
    Task task = new Task(TaskId.create(), subject, false);
    repository.save(task);
    return task;
  }
}

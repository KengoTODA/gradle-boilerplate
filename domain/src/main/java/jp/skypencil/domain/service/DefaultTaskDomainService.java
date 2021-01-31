package jp.skypencil.domain.service;

import jp.skypencil.domain.model.Task;
import jp.skypencil.domain.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The DomainService handling task related operations that should not be behaviour of the {@link
 * Task} class.
 */
@Service
class DefaultTaskDomainService implements TaskDomainService {
  private final TaskRepository repository;

  @Autowired
  DefaultTaskDomainService(TaskRepository repository) {
    this.repository = repository;
  }

  @Override
  public boolean duplicates(String subject) {
    return repository.exists(subject);
  }
}

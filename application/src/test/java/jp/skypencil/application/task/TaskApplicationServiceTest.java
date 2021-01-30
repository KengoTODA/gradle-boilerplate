package jp.skypencil.application.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import jp.skypencil.domain.model.TaskRepository;
import jp.skypencil.domain.service.TaskDomainService;
import jp.skypencil.infrastructure.onmemory.task.OnMemoryTaskRepository;
import org.junit.jupiter.api.Test;

class TaskApplicationServiceTest {
  @Test
  void testFind() {
    TaskRepository repository = new OnMemoryTaskRepository();
    TaskDomainService domainService =
        new TaskDomainService() {
          @Override
          public boolean duplicates(String subject) {
            return false;
          }
        };
    TaskApplicationService service = new DefaultTaskApplicationService(domainService, repository);
    UUID id = service.create("subject").getId();

    assertTrue(service.find(id).isPresent());
  }

  @Test
  void testFindRandomId() {
    TaskRepository repository = new OnMemoryTaskRepository();
    TaskDomainService domainService =
        new TaskDomainService() {
          @Override
          public boolean duplicates(String subject) {
            return false;
          }
        };
    TaskApplicationService service = new DefaultTaskApplicationService(domainService, repository);

    assertTrue(service.find(UUID.randomUUID()).isEmpty());
  }
}

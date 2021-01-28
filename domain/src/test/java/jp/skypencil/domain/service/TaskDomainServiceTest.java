package jp.skypencil.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;
import jp.skypencil.domain.model.Task;
import jp.skypencil.domain.model.TaskId;
import jp.skypencil.domain.model.TaskRepository;
import org.junit.jupiter.api.Test;

class TaskDomainServiceTest {
  @Test
  void testCreate() {
    TaskRepository repository =
        new TaskRepository() {
          @Override
          public void save(Task task) {}

          @Override
          public Optional<Task> find(TaskId id) {
            return Optional.empty();
          }

          @Override
          public Stream<Task> list() {
            return Stream.empty();
          }
        };

    TaskDomainService service = new TaskDomainService(repository);
    Task created = service.create("subject");
    assertEquals("subject", created.subject());
  }
}

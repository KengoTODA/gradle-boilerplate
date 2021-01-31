package jp.skypencil.domain.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.stream.Stream;
import jp.skypencil.domain.model.Task;
import jp.skypencil.domain.model.TaskId;
import jp.skypencil.domain.model.TaskRepository;
import org.junit.jupiter.api.Test;

class TaskDomainServiceTest {
  @Test
  void testDuplicates() {
    TaskRepository repository =
        new TaskRepository() {
          @Override
          public void save(Task task) {}

          @Override
          public Optional<Task> find(TaskId id) {
            return Optional.empty();
          }

          @Override
          public boolean exists(String subject) {
            return "subject".equals(subject);
          }

          @Override
          public Stream<Task> list() {
            return Stream.empty();
          }
        };

    DefaultTaskDomainService service = new DefaultTaskDomainService(repository);
    assertTrue(service.duplicates("subject"));
    assertFalse(service.duplicates("anotherSubject"));
  }
}

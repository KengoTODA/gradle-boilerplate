package jp.skypencil.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import jp.skypencil.application.task.DefaultTaskApplicationService;
import jp.skypencil.application.task.TaskData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(SpringExtension.class)
class TaskControllerTest {
  @MockBean DefaultTaskApplicationService service;

  @Test
  void testCreateTask() {
    TaskController controller = new TaskController(service);
    given(service.create(anyString()))
        .will(
            invocation -> {
              String subject = invocation.getArgument(0);
              return new TaskData(UUID.randomUUID(), subject, false);
            });

    TaskData data = controller.create("subject");
    assertEquals("subject", data.getSubject());
  }

  @Test
  void testListTask() {
    TaskController controller = new TaskController(service);
    given(service.listAll()).willReturn(Stream.empty());

    assertEquals(0, controller.list().count());
  }

  @Test
  void testTaskNotFound() {
    TaskController controller = new TaskController(service);

    UUID id = UUID.randomUUID();
    given(service.find(id)).willReturn(Optional.empty());
    String message = String.format("No task found with id: %s", id);
    assertThrows(ResponseStatusException.class, () -> controller.find(id), message);
  }
}

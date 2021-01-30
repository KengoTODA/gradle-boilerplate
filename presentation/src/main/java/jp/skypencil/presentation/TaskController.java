package jp.skypencil.presentation;

import java.util.UUID;
import java.util.stream.Stream;
import jp.skypencil.application.task.TaskApplicationService;
import jp.skypencil.application.task.TaskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * The Controller handling feature regarding tasks. Just translate rest-controller related values to
 * help domain objects understand requests from user.
 */
@RestController
class TaskController {
  private final TaskApplicationService service;

  @Autowired
  TaskController(TaskApplicationService service) {
    this.service = service;
  }

  @GetMapping("/task/{id}")
  TaskData find(@PathVariable("id") UUID id) {
    return service
        .find(id)
        .orElseThrow(
            () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with id: " + id));
  }

  @GetMapping("/task")
  Stream<TaskData> list() {
    return service.listAll();
  }

  @PostMapping("/task")
  TaskData create(@RequestBody String subject) {
    // TODO make sure subject is given properly
    return service.create(subject);
  }
}

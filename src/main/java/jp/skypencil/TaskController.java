package jp.skypencil;

import java.util.UUID;
import java.util.stream.Stream;
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
public class TaskController {
  private final TaskDomainService service;

  @Autowired
  public TaskController(TaskDomainService service) {
    this.service = service;
  }

  @GetMapping("/task/{id}")
  public TaskModel find(@PathVariable("id") UUID id) {
    return service
        .find(id)
        .orElseThrow(
            () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with id: " + id));
  }

  @GetMapping("/task")
  public Stream<TaskModel> list() {
    return service.list();
  }

  @PostMapping("/task")
  public TaskModel create(@RequestBody String subject) {
    return service.create(subject);
  }
}

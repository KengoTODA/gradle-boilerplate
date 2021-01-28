package jp.skypencil;

import java.util.UUID;
import java.util.stream.Stream;
import jp.skypencil.application.task.TaskData;
import jp.skypencil.domain.model.TaskId;
import jp.skypencil.domain.model.TaskRepository;
import jp.skypencil.domain.service.TaskDomainService;
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
  private final TaskRepository repository;

  @Autowired
  public TaskController(TaskDomainService service, TaskRepository repository) {
    this.service = service;
    this.repository = repository;
  }

  @GetMapping("/task/{id}")
  public TaskData find(@PathVariable("id") UUID id) {
    return repository
        .find(new TaskId(id))
        .map(TaskData::new)
        .orElseThrow(
            () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with id: " + id));
  }

  @GetMapping("/task")
  public Stream<TaskData> list() {
    return repository.list().map(TaskData::new);
  }

  @PostMapping("/task")
  public TaskData create(@RequestBody String subject) {
    // TODO make sure subject is given properly
    return new TaskData(service.create(subject));
  }
}

package jp.skypencil.application.task;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface TaskApplicationService {
  TaskData create(String subject) throws TaskDuplicationException;

  Optional<TaskData> find(UUID id);

  Stream<TaskData> listAll();

  Stream<TaskData> listToDo();
}

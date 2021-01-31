package jp.skypencil.infrastructure.onmemory.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jp.skypencil.domain.model.ImmutableTask;
import jp.skypencil.domain.model.TaskId;
import org.junit.jupiter.api.Test;

class OnMemoryTaskRepositoryTest {
  @Test
  void testInitialSize() {
    OnMemoryTaskRepository repository = new OnMemoryTaskRepository();
    assertEquals(0, repository.list().count());
  }

  @Test
  void testExists() {
    OnMemoryTaskRepository repository = new OnMemoryTaskRepository();
    assertFalse(repository.exists("subject"));

    repository.save(
        ImmutableTask.builder().id(TaskId.create()).subject("subject").isDone(false).build());
    assertTrue(repository.exists("subject"));
  }
}

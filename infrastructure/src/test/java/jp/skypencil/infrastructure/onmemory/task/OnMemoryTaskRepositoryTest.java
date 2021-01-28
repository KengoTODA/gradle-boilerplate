package jp.skypencil.infrastructure.onmemory.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OnMemoryTaskRepositoryTest {
  @Test
  void testInitialSize() {
    OnMemoryTaskRepository repository = new OnMemoryTaskRepository();
    assertEquals(0, repository.list().count());
  }
}

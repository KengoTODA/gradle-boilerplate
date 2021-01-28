package jp.skypencil.domain.model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TaskIdTest {
  @Test
  void testCreate() {
    assertNotEquals(TaskId.create(), TaskId.create());
  }
}

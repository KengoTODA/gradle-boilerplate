package jp.skypencil;

import java.util.Objects;
import java.util.UUID;

class TaskId implements Comparable<TaskId> {
  private final UUID uuid;

  TaskId(UUID uuid) {
    this.uuid = Objects.requireNonNull(uuid);
  }

  UUID getUuid() {
    return uuid;
  }

  static TaskId create() {
    return new TaskId(UUID.randomUUID());
  }

  @Override
  public int compareTo(TaskId another) {
    return this.uuid.compareTo(another.uuid);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskId taskId = (TaskId) o;
    return Objects.equals(uuid, taskId.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}

package jp.skypencil.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class TaskId implements Comparable<TaskId> {
  private final UUID uuid;

  // TODO narrow down the scope
  public TaskId(UUID uuid) {
    this.uuid = Objects.requireNonNull(uuid);
  }

  public UUID getUuid() {
    return uuid;
  }

  public static TaskId create() {
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

package jp.skypencil.domain.model;

import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public abstract class TaskId {
  public abstract UUID uuid();

  public static TaskId create() {
    return ImmutableTaskId.builder().uuid(UUID.randomUUID()).build();
  }
}

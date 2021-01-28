package jp.skypencil.domain.model;

import org.immutables.value.Value;

/**
 * The mutable entity representing task to handle.
 *
 * <p>We use only {@link #id} to identify the instance, because other fields could be updated by
 * user requests.
 */
@Value.Immutable
public abstract class Task {
  public abstract TaskId id();

  public abstract String subject();

  public abstract boolean isDone();
}

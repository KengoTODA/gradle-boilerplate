package jp.skypencil;

import java.util.Objects;

/**
 * The mutable entity representing task to handle.
 *
 * We use only {@link #id} to identify the instance, because other fields could be updated by user requests.
 */
class Task {
    private final TaskId id;
    private final String subject;
    private final boolean done;

    Task(TaskId id, String subject, boolean done) {
        this.id = Objects.requireNonNull(id);
        this.subject = Objects.requireNonNull(subject);
        this.done = done;
    }

    // TODO: confirm the usage of the getter
    TaskId getId() {
        return id;
    }

    // TODO: confirm the usage of the getter
    String getSubject() {
        return subject;
    }

    // TODO: confirm the usage of the getter
    boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package jp.skypencil.application.task;

public class TaskDuplicationException extends RuntimeException {
  public TaskDuplicationException() {
    super("The requested task already exists");
  }
}

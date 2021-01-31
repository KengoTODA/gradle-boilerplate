declare type TaskData = {
  id: string;
  subject: string;
};
declare function append(
  task: TaskData,
  list: DocumentFragment | HTMLUListElement
): void;

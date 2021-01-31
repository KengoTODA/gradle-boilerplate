type TaskData = {
  id: string;
  subject: string;
};

function append(
  task: TaskData,
  list: DocumentFragment | HTMLUListElement
): void {
  const item = document.createElement("li");
  item.textContent = `${task.subject} (${task.id})`;
  list.appendChild(item);
}

document.addEventListener("DOMContentLoaded", () => {
  const form = document.createElement("form");
  form.id = "form";
  form.action = "/task";
  form.method = "post";
  const label = document.createElement("label");
  label.innerText = "subject:";
  const subject = document.createElement("input");
  subject.name = "subject";
  subject.autofocus = true;
  label.appendChild(subject);
  const button = document.createElement("button");
  button.type = "submit";
  button.textContent = "Submit";
  form.appendChild(label).appendChild(button);

  const tasks = document.createElement("ul");
  tasks.id = "task";

  document.body.appendChild(form).appendChild(tasks);

  fetch("/task")
    .then((res) => res.json())
    .then((list: TaskData[]) => {
      const fragment = document.createDocumentFragment();
      list.forEach((task) => {
        append(task, fragment);
      });
      tasks.appendChild(fragment);
    });

  form.addEventListener("submit", (event) => {
    fetch("/task", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: subject.value,
    })
      .then((res) => res.json())
      .then((json) => append(json, tasks)); // TODO check status code
    subject.value = "";
    event.preventDefault();
  });
});

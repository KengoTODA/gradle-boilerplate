import {TaskData} from './data'
import {Form} from './Form'
import {ItemList} from './ItemList'
import {render} from 'react-dom'
import React from 'react';

class App extends React.Component<{}, {tasks:TaskData[]}> {
  constructor(props: {}) {
    super(props)
    this.state = {
      tasks: [],
    }
    this.onSubmit = this.onSubmit.bind(this)
  }
  componentDidMount() {
    fetch("/task")
      .then((res) => res.json())
      .then((list: TaskData[]) => this.setState(state => ({
        tasks: list,
      })))
  }
  onSubmit(subject: string) {
    fetch("/task", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: subject,
    })
      .then((res) => res.json())
      .then((json) => this.setState(state => ({
        tasks: [...state.tasks, json]
      }))); // TODO check status code
  }
  render() {
    return (<div>
      <Form onSubmit={this.onSubmit} />
      <ItemList items={this.state.tasks} />
    </div>)
  }
}

document.addEventListener("DOMContentLoaded", () => {
  const app = document.getElementById("app");
  render(<App />, app)
});

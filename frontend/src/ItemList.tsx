import React, { Component } from "react";
import { TaskData } from "./data";
import { List, ListItem } from "@material-ui/core";

class Item extends Component<{ item: TaskData }> {
  constructor(props: { item: TaskData }) {
    super(props);
  }
  render() {
    return (
      <ListItem>
        {this.props.item.subject} (id: {this.props.item.id})
      </ListItem>
    );
  }
}

export class ItemList extends React.Component<{ items: TaskData[] }> {
  constructor(props: { items: TaskData[] }) {
    super(props);
  }

  render() {
    const items = this.props.items.map((item) => (
      <Item key={item.id} item={item} />
    ));
    return <List id="task">{items}</List>;
  }
}

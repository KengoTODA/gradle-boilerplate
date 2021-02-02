import * as React from 'react'
import {TaskData} from './data'

class Item extends React.Component<{item:TaskData}> {
    constructor(props: {item:TaskData}) {
        super(props)
    }
    render() {
        return (<li key={this.props.item.id}>{this.props.item.subject} (id: {this.props.item.id})</li>)
    }
}

export class ItemList extends React.Component<{ items: TaskData[] }> {
    constructor(props: { items: TaskData[] }) {
        super(props)
    }

    render() {
        const items = this.props.items.map(item => (<Item item={item} />))
        return (<ul id="task">
            {items}
        </ul>)
    }
}
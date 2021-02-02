import * as React from 'react'

export class Form extends React.Component<{onSubmit: (subject: string) => void},{subject:string}> {
    constructor(props: {onSubmit: (subject: string) => void}) {
        super(props)
        this.state = {subject: ''}
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
    }
    handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        this.props.onSubmit(this.state.subject)
        this.setState(state => ({
            subject: '',
        }))
    }
    handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        this.setState(state => ({
            subject: e.target.value,
        }));  
    }
    render() {
        return (<form id="form" action="/task" method="post" onSubmit={this.handleSubmit}>
            <label>Subject: <input id="subject" type="text" onChange={this.handleChange} value={this.state.subject} autoFocus></input></label>
            <button type="submit">Submit</button>
        </form>)
    }
}
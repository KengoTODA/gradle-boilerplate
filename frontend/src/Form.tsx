import React, { Component } from 'react'
import { Button, TextField } from '@material-ui/core'
import { createStyles, withStyles, Theme, WithStyles } from '@material-ui/core/styles';

const useStyles = (theme: Theme) => createStyles({
  root: {
    '& .MuiTextField-root': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
})

interface Props extends WithStyles<typeof useStyles>{ }

class Form extends Component<Props & {onSubmit: (subject: string) => void},{subject:string}> {
    constructor(props: Props & {onSubmit: (subject: string) => void}) {
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
        const { classes } = this.props;

        return (<form className={classes.root} id="form" action="/task" method="post" onSubmit={this.handleSubmit}>
            <TextField id="subject" label="Subject" type="text" onChange={this.handleChange} value={this.state.subject} autoFocus />
            <Button type="submit">Submit</Button>
        </form>)
    }
}

export default withStyles(useStyles)(Form)

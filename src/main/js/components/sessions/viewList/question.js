import React from 'react'


export default class Question extends React.PureComponent {
    constructor(props) {
        console.log("Question Props")
        console.dir(props);
        super(props);
        this.state = {user: {}, sessionId: 0};
    }

    componentDidMount() {
        fetch(this.props.userId).then(data => data.json()).then(results => {
            this.setState({
                user: results,
                sessionId: this.props.sessionId
        })});
    }

    render() {
        console.log("Props from question")
        console.dir(this.props.userId)
        return (
                <tr>
                    <td>{this.state.user.firstName}</td>
                    <td>{this.props.text}</td>
                    {this.props.answer ? <td> {this.props.answer} </td> : null}
                </tr>
        )
    }
}
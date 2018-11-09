import React from 'react'


export default class Session extends React.PureComponent {
    constructor(props) {
        super (props)
    }

    render() {
        return (
            <tr>
                <td>{this.props.event.entity.hostName}</td>
                <td>{this.props.event.entity.title}</td>
                <td>{this.props.event.entity.startTime}</td>
                <td>{this.props.event.entity.endTime}</td>
                <td>{this.props.event.entity.topic}</td>
                <td> <button className='join' onClick={this.handleJoin}>Join</button></td>
             </tr>
        )
    }
}
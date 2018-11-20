import React from 'react'
import {Link} from "react-router-dom";

export default class Session extends React.PureComponent {
    constructor(props) {
        super (props)
    }

    render() {
        let splitSelfLink = this.props.event.entity._links.self.href.split("/");
        let entityLocation = splitSelfLink[splitSelfLink.length -1];
        let propsToPass = this.props.event.entity; 
        return (
            <tr>
                <td>{this.props.event.entity.hostName}</td>
                <td>{this.props.event.entity.title}</td>
                <td>{this.props.event.entity.startTime}</td>
                <td>{this.props.event.entity.endTime}</td>
                <td><Link to={{ pathname: `/session/${entityLocation}`, state: { entity: propsToPass } }}><button className='join'> Join!</button> </Link></td>
                {/*<td> <button className='join' onClick={this.handleJoin}>Join</button></td>*/}
             </tr>
        )
    }
}
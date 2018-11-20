import React from 'react'
import {Link} from "react-router-dom";

export default class Session extends React.PureComponent {
    constructor(props) {
        super (props)
    }

    render() {
        let splitSelfLink = this.props.event.entity._links.self.href.split("/");
        let entityLocation = splitSelfLink[splitSelfLink.length -1];
        let propsToPass = {
            entity: this.props.event.entity, 
            host: this.props.event.entity._links.host.href
        }; 
        
        console.dir(this.props.event)
        let startDate = new Date(this.props.event.entity.startTime); 
        let endDate = new Date(this.props.event.entity.endTime);
        return (
            <tr>
                <td>{this.props.event.entity.hostName}</td>
                <td>{this.props.event.entity.title}</td>
                <td>{`${startDate.toDateString()} ${startDate.toLocaleTimeString()}`}</td>
                <td>{`${endDate.toDateString()} ${endDate.toLocaleTimeString()}`}</td>
                <td><Link to={{ pathname: `/session/${entityLocation}`, state:  propsToPass }}><button className='join'> Join!</button> </Link></td>
                {/*<td> <button className='join' onClick={this.handleJoin}>Join</button></td>*/}
             </tr>
        )
    }
}
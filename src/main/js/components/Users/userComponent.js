import React from 'react'
import UpdateDialog from '../updateComponent'

export default class User extends React.PureComponent{
    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.user);
    }


    render() {

        return (
            <tr>
                <td>{this.props.user.entity.firstName}</td>
                <td>{this.props.user.entity.lastName}</td>
                <td>{this.props.user.entity.emailAddress}</td>
                <td>{this.props.user.entity.educationalInstitution}</td>
                <td>
                    <UpdateDialog user={this.props.user}
                                  attributes={this.props.attributes}
                                  onUpdate={this.props.onUpdate}/>
                </td>
                <td>
                    <button className='delete' onClick={this.handleDelete}>Delete</button>
                </td>
                <td> <button className='join' onClick={this.handleJoin}>Join</button>
                </td>
            </tr>
        );
    }
}
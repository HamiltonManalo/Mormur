import React from 'react'
import ReactDOM from 'react-dom'

export default class UpdateDialog extends React.PureComponent {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        let updatedUser = {};
        this.props.attributes.forEach(attribute => {
            updatedUser[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onUpdate(this.props.user, updatedUser);
        window.location = "#";
    }

    render() {
        let inputs = this.props.attributes.map(attribute =>
            <p key={attribute}>
                <input type="text" placeholder={attribute} defaultValue={this.props.user.entity[attribute]}
                       ref={attribute} className="field"/>
            </p>

        );

        let dialogId = "updateUser-" + this.props.user.entity._links.self.href;

        return (
            <div key={this.props.user.entity._links.self.href}>
                <a href={"#" + dialogId}>Update</a>
                <div id={dialogId} className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>

                        <h2>Update User</h2>

                        <form>
                            {inputs}
                            <button className='edit' onClick={this.handleSubmit}>Update</button>
                        </form>
                        
                    </div>
                </div>
            </div>
        )
    }
}
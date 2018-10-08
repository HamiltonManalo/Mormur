import React from 'react'
import ReactDOM from 'react-dom'

export default class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit(e) {
        e.preventDefault();
        let newUser = {};
        this.props.attributes.forEach(attribute => {
            newUser[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newUser);
        this.props.attributes.forEach(attribute => {
            ReactDOM.findDOMNode(this.refs[attribute]).value = '';
        });
        window.location = "#";
    }

    render() {
        // if(this.props.attributes)
        //Need backend User Model updated to reflect educational institute data point
        let inputs = this.props.attributes.map(attribute =>
            <p key={attribute}>
                <input type="text" placeholder={attribute} ref={attribute} className="field"/>
            </p>
        );

        return (
            <div>
                <div>
                    <button className='createbtn'>
                        <a className='test' href="#createUser">Create Session</a>
                    </button>
                </div>
                <div id="createUser" className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>
                        <h2>Create new user</h2>
                        <form>
                            {inputs}
                            <button className='create' onClick={this.handleSubmit}>Create</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

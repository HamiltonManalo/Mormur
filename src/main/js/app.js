const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {


    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/'}).done(response => {
            this.loadFromServer(this.state.pageSize);
        });
    }

    loadFromServer(pageSize) {
        follow(client, root, [
            {rel: 'users', params: {size: pageSize}}]
        ).then(userCollection => {
            return client({
                method: 'GET',
                path: userCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                return userCollection;
            });
        }).done(userCollection => {
            this.setState({
                users: userCollection.entity._embedded.users,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: userCollection.entity._links});
        });
    }
    render() {
        return (
            <UserList users ={this.state.users}/>
        );
    }

    onCreate(newUser) {
        follow(client, root, ['users']).then(employeeCollection => {
            return client({
                method: 'POST',
                path: userCollection.entity._links.self.href,
                entity: newUser,
                headers: {'Content-Type': 'application/json'}
            })
        }).then(response => {
            return follow(client, root,
                [{rel: 'users', params: {'size': this.state.pageSize}}]);
        }).done(response => {
            if(typeof response.entity._links.last != "undefined") {
                this.onNavigate(response.entity._links.last.href);
            } else {
                this.onNavigate(response.entity._links.self.href);
            }
        });
    }

    onNavigate(navUri) {
        client({method: 'GET', path: navUri}).done(userCollection => {
            this.setState({
                users: userCollection.entity._embedded.users,
                attributes: this.state.attributes,
                pageSize: this.state.pageSize,
                links: userCollection.entity._links
            })
        });
    }
    onDelete(user) {
        client({method: 'DELETE', path: employee._links.self.href}).done(response => {
            this.loadFromServer(this.state.pageSize)
        });

    }
        handleInput(e) {
            e.preventDefault();
            var pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
            if(/^[0-9]+$/.test(pageSize)) {
                this.props.updatePageSize(pageSize);
            } else {
                ReactDOM.findDOMNode(this.ref.pageSize).value = pageSize.substring(0, pageSize.length -1);
            }
        }

}

class UserList extends React.Component {
    handleNavFirst(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.first.href);
    }
    handleNavPrev(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.prev.href);
    }
    handleNavNext(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.next.href);
    }
    handleNavLast(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.last.href);
    }
    render() {
        let users = this.props.users.map(user =>
            <User key={user._links.self.href} user={user}/>
        );
        let navLinks = [];
         if("first" in this.props.links) {
             navLinks.push(<button key="first" onClick={this.handleNavFirst}> &lt;&lt;</button>);
         }
         if("prev" in this.props.links) {
             navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt; </button>);
         }
         if("next" in this.props.links) {
             navLinks.push(<button key="next" onClick ={this.handleNavNext}> &gt; </button>);
         }
         if("last" in this.props.links) {
             navLinks.push(<button key="last" onClick={this.handleNavLast}> &gt;&gt; </button>);
         }

        return (
            <div>
                <input refs="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
                <table>
                    <tbody>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th></th>
                        </tr>
                        {users}
                    </tbody>
                </table>
                <div>
                    {navLinks}
                </div>
            </div>
        )
    }
}

class User extends React.Component{
    constructor(props) {
        super(props)
            this.handleDelete = this.handleDelete.bind(this);
    }
    handleDelete() {
        this.props.onDelete(this.props.user);
    }
    render() {
        return (
            <tr>
                <td>{this.props.user.firstName}</td>
                <td>{this.props.user.lastName}</td>
                <td>{this.props.user.emailAddress}</td>
                <td>
                    <button onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        );
    }
}

class CreateDialog extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit(e) {
        e.preventDefault();
        var newUser = {};
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
        var inputs = this.props.attributes.map(attribute =>
        <p key={attribute}>
                <input type="text" placeholder={attribute} ref={attribute} className="field"/>
        </p>
        );
        return (
            <div>
                <a href="#createUser">Create</a>

                <div id="createUser" className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>
                        <h2>Create new user</h2>
                        <form>
                            {inputs}
                            <button onClick={this.handleSubmit}>Create</button>
                            </form>
                    </div>
                </div>
            </div>
        )
    }
}
ReactDOM.render(
    <App />,
    document.getElementById('react')
);
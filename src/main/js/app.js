"use strict";
import when from 'when';
import {
    BrowserRouter as Router,
    Route,
    Switch
} from 'react-router-dom';

const follow = require('./follow');
const root = '/api';
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
import Header from './components/headerComponent'
import SessionView from "./components/sessions/sessionViewComponent";
import UserView from "./components/Users/userViewComponent";
import SessionDetailsView from "./components/sessions/viewList/SessionDetailsView";


class App extends React.Component {


    constructor(props) {
        super(props);
        this.state = {users: [], attributes: [], pageSize: 2, links: {}};
        ///Context binding

    }

    componentDidMount() {
        client({
            method: 'GET',
            path: '/api/'
        }).done(response => {
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
                this.links = userCollection.entity._links;
                return userCollection;
            });
        }).then(userCollection => {
            return userCollection.entity._embedded.users.map(user =>
                client({
                    method: 'GET',
                    path: user._links.self.href
                })
            );
        }).then(userPromises => {
            return when.all(userPromises);
        }).done(users => {
            this.setState({
                users: users,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: this.links
            });
        });
    }
    render() {
        return (
            <div>
                <Header/>
                <div className="app">
                    <div className="page">
                            <Switch>
                                <Route exact path="/" component={Home}/>
                                <Route exact path="/sessionview" component={SessionView}/>
                                <Route exact path="/userview" component={UserView}/>
                                <Route path="/session/:id" component={SessionDetailsView}/>
                            </Switch>
                    </div>
                </div>
            </div>
        )
    }
}

const Home = () => (
    <div>
        <h2>Home</h2>
    </div>
)

ReactDOM.render(
    <Router>
    <App />
    </Router>,
    document.getElementById('root')
);
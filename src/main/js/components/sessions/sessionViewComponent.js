'use strict';
import React from 'react'
import client from "../../client";
import follow from "../../follow";
import when from "when";
import CreateDialog from "../createComponent";
import SessionList from "./sessionListComponent";

const root = '/api';

export default class SessionView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {events: [], attributes: [], pageSize: 10, links: {}};
    }

    componentWillMount(){
        client({
            method: 'GET',
            path: '/api/'
        }).done(() => {
            this.loadFromServer(this.state.pageSize);
        });
    }

    loadFromServer(pageSize) {
        follow(client, root, [
            {rel: 'sessionDetailses', params: {size: pageSize}}]
        ).then(eventCollections => {
            return client({
                method: 'GET',
                path: eventCollections.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                this.links = eventCollections.entity._links;
                return eventCollections;
            });
        }).then(eventCollection => {
            return eventCollection.entity._embedded.sessionDetailses.map(event =>
                client({
                    method: 'GET',
                    path: event._links.self.href
                })
            );
        }).then(eventPromises => {
            return when.all(eventPromises);
        }).done(events => {
            this.setState({
                events: events,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: this.links
            });
        });
    }

    onCreate(newEvent) {
        let self = this;
        follow(client, root, ['sessionDetailses']).then(response => {
            return client({
                method: 'POST',
                path: response.entity._links.self.href,
                entity: newEvent,
                headers: {'Content-Type': 'application/json'}
            })
        }).then(response => {
            return follow(client, root,
                [{rel: 'sessionDetailses', params: {'size': self.state.pageSize}}]);
        }).done(response => {
            if(typeof response.entity._links.last != "undefined") {
                this.onNavigate(response.entity._links.last.href);
            } else {
                this.onNavigate(response.entity._links.self.href);
            }
        });
    }

    onUpdate(event, updatedEvent) {
        client({
            method: 'PUT',
            path: event.entity._links.self.href,
            entity: updatedEvent,
            headers: {
                'Content-Type': 'application/json',
                'If-Match': user.headers.Etag
            }
        }).done(response => {
            this.loadFromServer(this.state.pageSize);

        }, response =>{
            if (response.status.code === 412) {
                alert("DENIED: Unable to update " + user.entity._links.self.href + ". Your copy is stale.")
            }
        });
    }
    
    onDelete(event) {
        client({method: 'DELETE', path: event.entity._links.self.href}).done(response => {
            this.loadFromServer(this.state.pageSize)
        });

    }

    onJoin(event) {
        client({method: 'PUT', path: event.entity._links.self.href}).done(responses => {
            this.loadFromServer(this.state.pageSize)
        });
    }

    onNavigate(navUri) {
        client({
            method: 'GET',
            path: navUri
        }).then(eventCollection => {
            this.links = eventCollection.entity._links;

            return eventCollection.entity._embedded.events.map(event =>
                client({
                    method: 'GET',
                    path: event._links.self.href
                })
            );
        }).then(eventPromises => {
            return when.all(eventPromises)
        }).done(events => {
            console.log("Printing API call")
            console.dir(events)
            this.setState({
                events: events,
                attributes: Object.keys(this.schema.properties),
                pageSize: this.state.pageSize,
                links: this.links,
            });
        });
    }

    updatePageSize(pageSize) {
        if (pageSize !== this.updatePageSize()) {
            this.loadFromServer(pageSize);
        }
    }

    render() {
        return (

            <div className='maincontainer'>
                <h2>Available Sessions</h2>
                
                <SessionList events={this.state.events}
                            links={this.state.links}
                            pageSize={this.state.pageSize}
                            attributes={this.state.attributes}
                            onNavigate={this.onNavigate}
                            onUpdate={this.onUpdate}
                            onDelete={this.onDelete}
                            onJoin={this.onJoin}
                            updatePageSize={this.updatePageSize}/>
                
                <CreateDialog className='test' 
                                attributes={this.state.attributes} 
                                onCreate={this.onCreate}/>
            </div>
        )
    }
}
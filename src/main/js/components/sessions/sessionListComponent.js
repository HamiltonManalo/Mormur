import React from 'react'
import ReactDOM from 'react-dom'
import Session from './sessionComponent'

export default class SessionList extends React.PureComponent {
    constructor(props) {
        super(props);
        this.handleNavFirst = this.handleNavFirst.bind(this);
        this.handleNavPrev = this.handleNavPrev.bind(this);
        this.handleNavNext = this.handleNavNext.bind(this);
        this.handleNavLast = this.handleNavLast.bind(this);
        this.handleInput = this.handleInput.bind(this);
    }
    handleInput(e) {
        e.preventDefault();
        let pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
        if(/^[0-9]+$/.test(pageSize)) {
            this.props.updatePageSize(pageSize);
        } else {
            ReactDOM.findDOMNode(this.ref.pageSize).value = pageSize.substring(0, pageSize.length -1);
        }
    }
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
        let Sessions = this.props.events.map(event =>
            <Session key={event.entity._links.self.href}
                  event={event}
                  attributes={this.props.attributes}
                  onUpdate={this.props.onUpdate}
                  onDelete={this.props.onDelete}
                  onJoin={this.props.onJoin}
            />
        );
        let navLinks = [];
        if("first" in this.props.links) {
            navLinks.push(<button className='navigate' key="first" onClick={this.handleNavFirst}> &lt;&lt;</button>);
        }
        if("prev" in this.props.links) {
            navLinks.push(<button className='navigate' key="prev" onClick={this.handleNavPrev}>&lt; </button>);
        }
        if("next" in this.props.links) {
            navLinks.push(<button className='navigate' key="next" onClick ={this.handleNavNext}> &gt; </button>);
        }
        if("last" in this.props.links) {
            navLinks.push(<button className='navigate' key="last" onClick={this.handleNavLast}> &gt;&gt; </button>);
        }

        return (
            <div>
                <p> test </p>
                <input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
                <table>
                    <tbody>
                    <tr>
                        <th>Host Name</th>
                        <th>Title</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th></th>
                    </tr>
                    {Sessions}
                    </tbody>
                </table>
                <div>
                    {navLinks}
                </div>
            </div>
        )
    }
}
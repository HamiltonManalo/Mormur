'use strict';

import {Link} from "react-router-dom";
import React from 'react';

export default class Header extends React.PureComponent {

    constructor() {
        super();
    }

    render() {
        let pageText = "Mormur";
        return (
            <div className="header">
                <div className='banner'>
                    <div className='logo'>{pageText}</div>
                    <span>{/*user icon goes here */}</span>
                    <span>{/*Login/out button goes here*/}</span>
                </div> 
                    <div className='navbar'>
                        <span className='navelement'>
                            <Link to="/">
                                <button className='navbutton'>Home</button>
                            </Link>
                        </span>
                        <span className='navelement'>
                            <Link to="/sessionview">
                                <button className='navbutton'>Q &amp; A Sessions</button>
                            </Link>
                        </span>
                        <span className='navelement'>
                            <Link to="/userview">
                                <button className='navbutton'>Active Users</button>
                            </Link>
                        </span>
                    </div>
            </div>
        )
    }
}


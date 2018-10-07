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

                <div>
                    <div id='banner'>
                        <div id='logo'>{pageText}</div>
                        <div id='navbar'>
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
                </div>

            )
    }
}


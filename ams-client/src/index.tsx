import React from 'react'
import ReactDOM from 'react-dom'
import * as serviceWorker from './serviceWorker'

import App from './App'

import {Route, BrowserRouter as Router, Switch} from 'react-router-dom'
import WorkPage from './components/Work/WorkPage/WorkPage'

const routing = (
    <Router>
        <div className="page">
            <Switch>
                <Route exact path="/" component={App} />
                <Route exact path="/project/:id" component={WorkPage} />
            </Switch>
        </div>
    </Router>
)
ReactDOM.render(routing, document.getElementById('root'))

serviceWorker.unregister()
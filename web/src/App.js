import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {ToastContainer} from "react-toastify";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./page/Home";
import Login from "./page/Login";
import Cabinet from "./page/Cabinet";
import Register from "./page/Register";


class App extends Component {
    render() {
        return (
            <div className="container pt-2">
                <ToastContainer/>
                <Router>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/cabinet" component={Cabinet}/>
                        <Route exact path="/register" component={Register}/>
                    </Switch>
                </Router>
            </div>
        );
    }
}

App.propTypes = {};

export default App;
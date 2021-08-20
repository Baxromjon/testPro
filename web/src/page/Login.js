import React, {Component} from 'react';
import {AvField, AvForm} from 'availity-reactstrap-validation'
import request from "../utils/request";
import api from "../utils/api";
import {TOKEN} from "../utils/constant";


class Login extends Component {

    componentDidMount() {
        if (localStorage.getItem(TOKEN)) {
            this.props.history.push('/cabinet')
        } else {
        }
    }

    login = (e, v) => {
        request({
            url: api.loginUrl,
            method: 'POST',
            data: v
        }).then(res => {
            console.log(res.data)
            if (res.status === 200) {
                localStorage.setItem(TOKEN, 'Bearer ' + res.data.data)
                this.props.history.push('/cabinet')
            }
        }).catch(err => {
            alert(err.data)
        })
    };

    routeToRegister = () => {
        this.props.history.push('/register')
    }

    render() {
        return (
            <div className="align-content-center mt-2">
                <h4 className="text-center">Login page</h4>
                <AvForm onValidSubmit={this.login}>
                    <div className="mt-4">
                        <AvField
                            placeholder="enter phone number"
                            validate={{required: {value: true, errorMessage: "Please enter phoneNumber"}}}
                            name="phoneNumber"
                        />
                    </div>
                    <div className="mt-4">
                        <AvField
                            placeholder="enter password"
                            validate={{required: {value: true, errorMessage: "Please enter password"}}}
                            type="password"
                            name="password"
                        />
                    </div>
                    <button className="btn btn-success m-1">login</button>
                    <button className="btn btn-info m-2"
                            onClick={this.routeToRegister}>sign up
                    </button>
                </AvForm>

            </div>
        );
    }
}

Login.propTypes = {};

export default Login;
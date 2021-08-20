import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {TOKEN} from "../utils/constant";
import request from "../utils/request";
import api from "../utils/api";
import {AvField, AvForm} from "availity-reactstrap-validation";


class Register extends Component {
    componentDidMount() {
        if (localStorage.getItem(TOKEN)){
            this.props.history.push('/login')
        }else {

        }
    }
    register=(e,v)=>{
        console.log(v)
        request({
            url:api.registerUrl,
            method:'POST',
            data:v
        }).then(res=>{
            this.props.history.push('/login')
        }).catch(err=>{
            alert("xatolik")
        })
    }
    render() {
        return (
            <div>
                <h3 className="text-center">Register</h3>
                <AvForm onValidSubmit={this.register}>
                    <AvField
                    name="firstName"
                    placeholder="Enter First Name"
                    validate={{
                        required: {value: true, errorMessage: 'Please, enter your first name'}
                    }}/>
                    <AvField
                    name="lastName"
                    placeholder="Enter Last Name"
                    validate={{
                        required: {value: true, errorMessage: 'Please, enter your last name'}
                    }}/>
                    <AvField
                    name="middleName"
                    placeholder="Enter Middle Name"
                    validate={{
                        required: {value: true, errorMessage: 'Please, enter your middle name'}
                    }}/>
                    <AvField
                    name="phoneNumber"
                    placeholder="Enter phone number"
                    validate={{
                        required: {value: true, errorMessage: 'Please, enter your phone number'}
                    }}/>
                    <AvField
                    name="password"
                    type="password"
                    placeholder="Enter password"
                    validate={{
                        required: {value: true, errorMessage: 'Please, enter password'}
                    }}/>
                    <AvField
                    name="prePassword"
                    type="password"
                    placeholder="Enter again password"
                    validate={{
                        required: {value: true, errorMessage: 'Please, enter again password'}
                    }}/>

                    <button className="btn btn-success">Register</button>
                    <button className="btn btn-danger">Cancel</button>
                </AvForm>
            </div>
        );
    }
}

Register.propTypes = {};

export default Register;
import React, {Component} from 'react';
import PropTypes from 'prop-types';
import request from "../utils/request";
import api from "../utils/api";
import {TOKEN} from "../utils/constant";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {Modal, ModalHeader, ModalBody, ModalFooter} from "reactstrap";
import axios from "axios";
import {render} from "react-dom";


class Cabinet extends Component {
    state = {
        employee: [],
        currentEmployee: '',
        role: [],
        currentRole: '',
        users: [],
        currentUser: '',
        showModal: false,
        showModalRole: false,
        showModalEmployee: false,
        fieldss: [],
        fields: [
            "first_name",
            "last_name",
            "middle_name",
            "birth_date",
            "passport_serial",
            "passport_serial_number",
            "phone_number",
            "password"
        ]
    }
    getEmployee = () => {
        request({
            url: api.getEmployee,
            method: 'GET'
        }).then(res => {
            console.log(res.data);
            this.setState({employee: res.data})
        }).catch(err => {
            // alert("xatolik")
        })
    }
    getRole = () => {
        request({
            url: api.getRole,
            method: 'GET'
        }).then(res => {
            this.setState({role: res.data})
        }).catch(err => {
            // alert("xatolik")
        })
    }
    getUsers = () => {
        request({
            url: api.getUsers,
            method: 'GET'
        }).then(res => {
            this.setState({users: res.data})
        }).catch(err => {
        })
    }
    addRoleEmployee = (e, v) => {
        request({
            url: api.addRoleEmployee,
            method: 'POST',
            data: v
        }).then(res => {
            this.getRole()
            this.setState({showModal: false})
        }).catch(err => {
        })
    }
    addRoleEmp = (item) => {
        this.openModal()
        this.setState({
            currentRole: item
        })
    }
    saveEmployee = (e, v) => {
        request({
            url: api.adEmployee,
            method: 'POST',
            data: v
        }).then(res => {
            this.getEmployee()
            this.openModalEmployee()
        }).catch(err => {
            alert("xatolik")
        })
    }
    openModal = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }
    openModalRole = () => {
        this.setState({
            showModalRole: !this.state.showModalRole
        })
    }
    openModalEmployee = () => {
        this.setState({
            showModalEmployee: !this.state.showModalEmployee
        })
    }
    getUserMe = () => {

        // axios.get("http://localhost:8092/api/employee/me",{headers:{"Authorization":"Bearer "+TOKEN}}).then(res=>{
        //     console.log(res)
        // })
        request({
            url: api.userMe,
            method: 'GET',
        }).then(res => {
            this.setState({currentUser: res.data})
        }).catch(err => {
        })
    }
    getRoleEmployee = () => {
        request({
            url: api.getRoleEmployee,
            method: 'GET'
        }).then(res => {
            console.log(res.data)
            this.setState({fieldss: res.data})
        }).catch(err => {
        })
    }
    saveRole = (e, v) => {
        console.log(v)
        request({
            url: api.addRole,
            method: 'POST',
            data: v
        }).then(res => {
            this.getRole()
            this.openModalRole()
        }).catch(err => {
            alert("xatolik")
        })
    }

    componentDidMount() {
        if (!localStorage.getItem(TOKEN)) {
            this.props.history.push('/login')
        } else {
            this.getEmployee()
            this.getRole()
            this.getUsers()
            this.getUserMe()
            this.getRoleEmployee()
        }
    }


    render() {
        function nameFun(field, employe) {
            for (var prop in employe) {
                if (prop === toCamel(field)) {
                    return (
                        <tr>
                            <td>
                                {employe[prop]}
                            </td>
                        </tr>
                    );
                }
            }
        }

        const toCamel = (s) => {
            return s.replace(/([-_][a-z])/ig, ($1) => {
                return $1.toUpperCase()
                    .replace('-', '')
                    .replace('_', '');
            });
        };

        const {currentUser} = this.state;
        return (
            <div>
                <h3>Cabinet page</h3>
                <h5>{currentUser.firstName + ' ' + currentUser.lastName + ' ' + "welcome to cabinet"}</h5>
                <hr/>
                <br/>
                <h4>User table</h4>
                <hr/>
                <br/>
                <table className="table table-bordered text-center">
                    <thead>
                    <th>№</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Middle name</th>
                    <th>Phone number</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                    {this.state.users?.map((users, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{users.firstName}</td>
                            <td>{users.lastName}</td>
                            <td>{users.middleName}</td>
                            <td>{users.phoneNumber}</td>
                            <td>
                                <button className="btn btn-success">edit</button>
                                <button className="btn btn-danger">delete</button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
                <br/>
                <h4>Employee table <button className="btn btn-info rounded-circle"
                                           onClick={this.openModalEmployee}>+</button></h4>
                <hr/>
                <br/>
                <table className="table table-bordered text-center">
                    <thead>
                    <th>№</th>
                    {this.state.fieldss?.map((item, index) =>
                        <th>{item}</th>
                    )}
                    <th>Action</th>
                    </thead>
                    <tbody>
                    {this.state.employee?.map((employe) =>

                        this.state.fieldss?.map((field) =>
                            <tr>
                                <td>
                                    {nameFun(field, employe)}{console.log(field, employe)}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <br/>
                <h4>Role table <button className="btn btn-info rounded-circle"
                                       onClick={this.openModalRole}>+</button></h4>
                <button className="btn btn-info"
                        onClick={this.addRoleEmp}>Person attachment
                </button>
                <hr/>
                <br/>
                <table className="table table-bordered text-center">
                    <thead>
                    <th>№</th>
                    <th>Role name</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                    {this.state.role?.map((item, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{item.name}</td>
                            <td>
                                <button className="btn btn-success">edit</button>
                                <button className="btn btn-danger">delete</button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>

                <Modal isOpen={this.state.showModalRole}>
                    <ModalHeader>
                        Role qo`shish
                    </ModalHeader>
                    <ModalHeader>
                        <AvForm onValidSubmit={this.saveRole}>
                            <AvField
                                placeholder="enter role name"
                                validate={{required: {value: true, errorMessage: "Please enter role name"}}}
                                name="name"/>
                            <button className="btn btn-success">save</button>
                            <button className="btn btn-danger" onClick={this.openModalRole}>cancel</button>
                        </AvForm>
                    </ModalHeader>
                </Modal>
                <Modal isOpen={this.state.showModalEmployee}>
                    <ModalHeader>add Employee</ModalHeader>
                    <ModalBody>
                        <AvForm onValidSubmit={this.saveEmployee}>
                            <AvField
                                name="firstName"
                                placeholder="enter first name"/>
                            <AvField
                                name="lastName"
                                placeholder="enter last name"/>
                            <AvField
                                name="middleName"
                                placeholder="enter middle name"/>
                            <AvField
                                name="birthDate"
                                label="Date"
                                type="date"/>
                            <AvField
                                name="passportSerial"
                                placeholder="enter passport serial"/>
                            <AvField
                                name="passportSerialNumber"
                                placeholder="enter passport serial number"/>
                            <AvField
                                name="phoneNumber"
                                placeholder="enter phone number"/>
                            <AvField
                                name="password"
                                type="password"
                                placeholder="enter password"/>
                            <button className="btn btn-success">save</button>
                            <button className="btn btn-danger" onClick={this.openModalEmployee}>cancel</button>
                        </AvForm>
                    </ModalBody>
                </Modal>
                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>Rolega fieldlarni biriktirish</ModalHeader>
                    <ModalBody>
                        <AvForm onValidSubmit={this.addRoleEmployee}>
                            <AvField
                                type="select"
                                name="roleId">
                                <option value="">Select role</option>
                                {this.state.role?.map(role =>
                                    <option value={role.id}>{role.name}</option>)}
                            </AvField>
                            <AvField
                                type="select"
                                name="fields"
                                multiple>
                                <option value="">Select fields</option>
                                {this.state.fields.map(fields =>
                                    <option value={fields}>{fields}</option>)}
                            </AvField>
                            <button className="btn btn-success">save</button>
                            <button className="btn btn-danger"
                                    onClick={this.openModal}>cancel
                            </button>
                        </AvForm>
                    </ModalBody>
                </Modal>

            </div>
        );
    }
}

Cabinet.propTypes = {};

export default Cabinet;

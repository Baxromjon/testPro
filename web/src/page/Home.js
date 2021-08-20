import React, {Component} from 'react';

class Home extends Component {
routeToLogin=()=>{
    this.props.history.push("/login")
}
    render() {
        return (
            <div >
               <h5>sistemaga kirish uchun ro`yxatdan o`ting
               yoki login va parol yordamida kiring</h5>
                <div>
                    <button className="btn btn-success"
                    onClick={this.routeToLogin}>
                        login
                    </button>
                </div>
            </div>
        );
    }
}

Home.propTypes = {};

export default Home;
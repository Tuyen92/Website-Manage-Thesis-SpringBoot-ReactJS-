import Form from 'react-validation/build/form';
import Input from 'react-validation/build/input';
import CheckButton from "react-validation/build/button";
import React, { Component } from "react";
import { useNavigate, useParams } from 'react-router-dom';
import withRouter from '../common/withRouter'
import AuthenticationService from '../service/AuthenticationService';

  const required = value => {
      if (!value) {
        return (
          <div className="alert alert-danger" role="alert">
            This field is required!
          </div>
        );
      }
    };
  
  class Login extends Component {
    constructor(props) {
      super(props);
      this.handleLogin = this.handleLogin.bind(this);
      this.onChangeUsername = this.onChangeUsername.bind(this);
      this.onChangePassword = this.onChangePassword.bind(this);
  
      this.state = {
        username: "",
        password: "",
        loading: false,
        message: ""
      };
    }
  
    onChangeUsername(e) {
      this.setState({
        username: e.target.value
      });
    }
  
    onChangePassword(e) {
      this.setState({
        password: e.target.value
      });
    }

    
  
    handleLogin(e) {
      e.preventDefault();
  
      this.setState({
        message: "",
        loading: true
      });
  
      this.form.validateAll();
      
  
      if (this.checkBtn.context._errors.length === 0) {
        AuthenticationService.dangNhap(this.state.username, this.state.password).then(
          () => {
            this.props.navigate(`/thongtincanhan/:id`);
            // this.props.router.push('/thongtincanhan');
            window.location.reload();
          },
          error => {
            const resMessage =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
  
            this.setState({
              loading: false,
              message: resMessage
            });
          }
        );
      } else {
        this.setState({
          loading: false
        });
      }
    }
  
    render() {
      return (

        <div className='container'>
            <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Đăng nhập</h2>
                <Form
                    onSubmit={this.handleLogin}
                    ref={c => {
                    this.form = c;
                }}
                >
                <div className="row mt-4">
                <label className='col form-label'  htmlFor="username">Username</label>
                <Input
                  type="text"
                  className="col form-control"
                  name="username"
                  value={this.state.username}
                  onChange={this.onChangeUsername}
                  validations={[required]}
                  placeholder="Nhập username"
                />
                </div>
  
              <div className="row mt-4">
                <label htmlFor="password">Password</label>
                <Input
                  type="password"
                  className="col form-control"
                  name="password"
                  value={this.state.password}
                  onChange={this.onChangePassword}
                  validations={[required]}
                  placeholder="Nhập password"
                />
              </div>
  
              
                <button
                  className="btn btn-outline-primary mt-4"
                  disabled={this.state.loading}
                    >
                  {this.state.loading && (
                    <span className="spinner-border spinner-border-sm"></span>
                  )}
                  <span>Login</span>
                </button>
              
  
              {this.state.message && (
                <div className="form-group">
                  <div className="alert alert-danger" role="alert">
                    {this.state.message}
                  </div>
                </div>
              )}
              <CheckButton
                style={{ display: "none" }}
                ref={c => {
                  this.checkBtn = c;
                }}
              />
            </Form>
                
              
            </div>
          </div>
        </div>     
         
      );
    }
  }
  
  export default withRouter(Login);
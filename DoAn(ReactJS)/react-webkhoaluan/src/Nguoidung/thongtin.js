import React, { Component, useEffect, useState } from 'react'
import { Link, Navigate } from 'react-router-dom'
import AuthenticationService from '../service/AuthenticationService';

export default class thongtin extends Component
{

  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" }
    };
  }

  // componentDidMount() {
  //   const currentUser = AuthenticationService.getCurrentUser();

  //   if (!currentUser) this.setState({ redirect: "/" });
  //   this.setState({ currentUser: currentUser, userReady: true })
  // }
  
  render(){

    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />
    }

    const { currentUser } = this.state;
    
  return (
    <div className='container'>
      {/* {(this.state.userReady) ? */}
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
            
                <h2 className='text-center'>Trang thông tin người dùng : {currentUser.ho} {currentUser.ten}</h2>

                <div className='card mt-4 h-80' >
                  <div className='card-header'>
                    <ul className='list-group list-group-flush'>
                      <li className='list-group-item'>
                        <b>Họ và tên:</b>
                        {currentUser.ho} {currentUser.ten}
                      </li>
                      <li className='list-group-item'>
                        <b>Mã người dùng:</b>
                        {currentUser.username}
                      </li>
                      <li className='list-group-item'>
                        <b>Địa chỉ:</b>
                        {currentUser.diaChi}
                      </li>
                      <li className='list-group-item'>
                        <b>Email:</b>
                        {currentUser.email}
                      </li>
                      <li className='list-group-item'>
                        <b>Số điện thoại:</b>
                        {currentUser.sdt}
                      </li>
                    </ul>
                  </div>
                  <Link className='btn btn-primary my-2' to={"/"}>
                    Trở về trang chủ
                  </Link>
                </div>
            </div>
        </div>        
        {/* :null} */}
    </div>
  )
}
}
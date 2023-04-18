import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';

export default function 
() {

    let navigate = useNavigate();

    const {id} = useParams();

    const [nguoidung, setNguoidung]=useState({
        password:"",
        confirmPass:""
    })



    const onInputchange =(e) =>{
        setNguoidung({...nguoidung.password, [e.target.name]: e.target.value,
            ...nguoidung.confirmPass, [e.target.name]: e.target.value,
        });
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/capnhatND/${id}`, nguoidung);
        navigate("/");
    };

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Đổi mật khẩu tài khoản người dùng: {id}</h2>
                <h2>{nguoidung.ho} {nguoidung.ten}</h2>
                <hr></hr>
                <form onSubmit={(e) => onSubmit(e)} >
                <div className='row mt-4'>
                    <label htmlFor='password' className='col-md-4 form-label mt-2' >Mật khẩu mới:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập mật khẩu mới" name='password' defaultValue={nguoidung.password} onChange={(e) => onInputchange(e)}  />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='confirmPass' className='col-md-4 form-label mt-2' >Nhập lại mật khẩu mới:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập lại mật khẩu" name='confirmPass' defaultValue={nguoidung.confirmPass} onChange={(e) => onInputchange(e)}  />
                </div>

                <div className='row mt-4'>
                <button className='col-md-2 btn btn-outline-primary btn-block ml-3 mb-4 mt-2' type='submit' >Xác nhận</button>
                <button type="reset" className="col-md-2 btn btn-danger btn-block mb-4 ml-3 text-center">Hủy</button>
                </div>
                </form>
            </div>
        </div>
    </div>
  )
}
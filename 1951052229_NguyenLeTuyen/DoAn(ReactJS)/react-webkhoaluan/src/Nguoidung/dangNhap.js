import React, {  useEffect, useState } from 'react'
import axios from 'axios';
import { Link, useNavigate, useParams } from 'react-router-dom';


export default function 
() {

  let navigate = useNavigate;
  const [nguoidung, setnguoidung] = useState({
    username:"",
    password:"",
  });

  const onInputchange =(e) =>{
    setnguoidung({...nguoidung.username, [e.target.name]: e.target.value,
        ...nguoidung.password, [e.target.name]: e.target.value,
    });
};

 const dangnhap = async (e) =>{
  e.preventDefault();
  await axios.post(`http://localhost:8080/dangNhap`, nguoidung);
  navigate("/");
};

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Đăng nhập</h2>
                <form >
                <div className='row mt-4'>
                    <label htmlFor='username'  className='col form-label' >Username</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập username" defaultValue={nguoidung.username} />
                </div>
                <div className='row mt-4'>
                    <label htmlFor='password' className='col form-label' >Password</label>
                    <input className='col form-control' type={"password"} placeholder="Nhập password" defaultValue={nguoidung.password}/>
                </div>

                <button className='btn btn-outline-primary mt-4' type='submit' onClick={() => dangnhap()}>Đăng nhập</button>
              </form>
            </div>
          </div>
        </div>
  )
}
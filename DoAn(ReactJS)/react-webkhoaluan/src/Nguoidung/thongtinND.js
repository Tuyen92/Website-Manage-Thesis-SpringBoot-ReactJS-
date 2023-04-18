import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';

export default function 
() {

    let navigate = useNavigate();

    const {id} = useParams();

    const [nguoidung, setNguoidung]=useState({
        ho:"",
        ten:"",
        ngaySinh:"",
        anh : "",
        gioiTinh:"",
        diaChi:"",
        sdt:"",
        email:"",
        chucvu:{
            tenCv:""
        }
    })

    useEffect(() =>{
        loadNguoidung();
      }, [])

    const loadNguoidung = async(e) => {
        const result = await axios.get(`http://localhost:8080/nguoidung/${id}`);
        setNguoidung(result.data);
    }

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Thông tin tài khoản người dùng: </h2>
                <h2>{nguoidung.ho} {nguoidung.ten}</h2>
                <hr></hr>

                <div className='card mt-4 h-80' >
                  <div className='card-header'>
                    <ul className='list-group list-group-flush'>
                      <li className='list-group-item mt-2'>
                        <b>Họ Tên:</b>
                        {nguoidung.ho} {nguoidung.ten}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Ngày sinh: </b>
                        {nguoidung.ngaySinh}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Giới tính: </b>
                        {nguoidung.gioiTinh}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Địa chỉ: </b>
                        {nguoidung.diaChi}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Email: </b>
                        {nguoidung.email}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>SDT: </b>
                        {nguoidung.sdt}
                      </li>
                      
                    </ul>
                  </div>
                  <Link className='btn btn-primary my-2 mt-4' to={`/capNhatMK/${id}`}>Cập nhật mật khẩu</Link>
                  <Link className='btn btn-primary my-2' to={"/"}>Trở về trang chủ</Link>
                </div>
                
                
            </div>
        </div>
    </div>
  )
}
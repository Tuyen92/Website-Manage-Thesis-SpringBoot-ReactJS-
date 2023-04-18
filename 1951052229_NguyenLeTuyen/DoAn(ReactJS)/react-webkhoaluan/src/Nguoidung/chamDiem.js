import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';




export default function 
() {

    let navigate = useNavigate();

    const [diem, setDiem] = useState({
        diem:"",
        nhanXet:""
    })

    const onInputchange =(e) =>{
        setDiem({...diem.diem, [e.target.name]: e.target.value,
            ...diem.nhanXet, [e.target.name]: e.target.value,
        });
       
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/giangvien/chamDiem`, diem);
        navigate("/quantri/QLTaiKhoan");
    };

    // const {ho,ten,nguoidungPK:{maCv,maNd},} = nguoidung;

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Chấm điểm khóa luận</h2>
                <hr></hr>
                <form onSubmit={(e) => onSubmit(e)}>
                <div className='row mt-4'>
                    <label htmlFor='diem' className='col-md-3 form-label' >Điểm số:</label>
                    <input className='col form-control' type={"number"} placeholder="Điểm số" name='file' defaultValue={diem.diem} onChange={(e) => onInputchange(e)}  />
                </div>
                <div className='row mt-4'>
                    <label htmlFor='nhanXet' className='col-md-3 form-label' >Cho nhận xét:</label>
                    <input className='col form-control' type={"text"} placeholder="Cho nhận xét" name='file' defaultValue={diem.nhanXet} onChange={(e) => onInputchange(e)}  />
                </div>
                <button className='btn btn-outline-primary mt-4' type='submit' >Chấm điểm</button>
                <Link className='btn btn-danger mx-2 mt-4' to="/quantri/QLTaiKhoan">Hủy</Link>
                </form>
            </div>
        </div>
    </div>
  )
}
import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';


export default function 
() {

    let navigate = useNavigate();

    

    const [detai, setDetai]=useState({
        maDt: "",
        tenDt: "",
        noiDunngdt: "",
        hanNop: "",
        khoaMaKhoa: {
            maKhoa: "",
            tenKhoa: "",
            vanPhong: "",
            emailKhoa: "",
            sdtKhoa: "",
            website: ""
        }
    })

    const onInputchange =(e) =>{
        setDetai({...detai, [e.target.name]: e.target.value});
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post("http://localhost:8080/giaovu/themDeTai", detai);
        navigate("/detai");
    };

    // const {tenDt,noiDung, maKhoa} = detai;

  return (
    
        <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Thêm đề tài khóa luận</h2>
                <form onSubmit={(e) => onSubmit(e)}>
                <div className='row mt-4'>
                    <label htmlFor='maDt' className='col form-label' >
                        Mã đề tài:
                    </label>
                    <input  className='col form-control' placeholder="Nhập mã đề tài" name="maDt" value={detai.maDt} onChange={(e) => onInputchange(e)} />
                </div>
                <div className='row mt-4'>
                    <label htmlFor='tenDt' className='col form-label' >
                        Tên Đề tài:
                    </label>
                    <input className='col form-control' type={"text"} placeholder="Nhập tên đề tài" name="tenDt" value={detai.tenDt} onChange={(e) => onInputchange(e)}  />
                </div>
                <div className='row mt-4'>
                    <label htmlFor='detai.noiDung' className='col form-label' >
                        Nội dung đề tài:
                    </label>
                    <textarea  className='col form-control' placeholder="Nhập nội dung" name='detai.noiDung' value={detai.noiDung} onChange={(e) => onInputchange(e)} />
                </div>
                <div className='row mt-4'>
                    <label htmlFor='maKhoa' className='col form-label' >
                        Mã khoa:
                    </label>
                    <input className='col form-control' type={"text"} placeholder="Nhập khoa" name="khoaMaKhoa" value={detai.khoaMaKhoa.maKhoa} onChange={(e) => onInputchange(e)}  />
                </div>
                <button className='btn btn-outline-primary mt-4' type='submit'>
                    Xác nhận
                </button>
                <Link className='btn btn-danger mx-2 mt-4' to="/quantri/QLTaiKhoan">
                    Hủy
                </Link>
                </form>
            </div>
        </div>
        </div>
        
  )
}

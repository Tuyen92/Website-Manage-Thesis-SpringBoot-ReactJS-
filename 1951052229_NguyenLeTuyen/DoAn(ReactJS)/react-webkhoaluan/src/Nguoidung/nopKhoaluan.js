import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';




export default function 
() {

    let navigate = useNavigate();

    const [khoaluan, setKhoaluan] = useState({
        file:"",
        ghiChu:""
    })

    const onInputchange =(e) =>{
        setKhoaluan({...khoaluan.tenHd, [e.target.name]: e.target.value,
            ...khoaluan.maHd, [e.target.name]: e.target.value,
        });
       
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/sinhvien/nopKL`, khoaluan);
        navigate("/quantri/QLTaiKhoan");
    };

    // const {ho,ten,nguoidungPK:{maCv,maNd},} = nguoidung;

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Nộp bài làm khóa luận</h2>
                <hr></hr>

                <form onSubmit={(e) => onSubmit(e)}>
                <div className='row mt-4'>
                    <label htmlFor='tenHd' className='col-md-3 form-label mt-2' >File bài khóa luận:</label>
                    <input className='col form-control' type={"file"} placeholder="Chọn file" name='file' defaultValue={khoaluan.file} onChange={(e) => onInputchange(e)}  />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='ghiChu' className='col-md-3 form-label mt-2' >Ghi chú:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập ghi chú (nếu có)" name='ghiChu' defaultValue={khoaluan.file} onChange={(e) => onInputchange(e)}  />
                </div>
                
                <button className='btn btn-outline-primary mt-4' type='submit' >
                    Nộp
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
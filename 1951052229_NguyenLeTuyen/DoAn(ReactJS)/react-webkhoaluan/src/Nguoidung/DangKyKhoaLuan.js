import React from 'react'
import { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function DangKyKhoaLuan() {
    const {id} = useParams();

    const [dangkykhoaluanPK, setDKPK] = useState({
        detaiMaDt:"",
        
    })

    const [dangkykhoaluan, setDK] = useState({
        // dangkykhoaluanPK: {
        //     sinhvienMaSv:""
        // },
        maSv2: "",
        // detai: {
        //     maDt: "",
        // },
        sinhvien: {
            maSv: "",
        }
    })

    let navigate = useNavigate();
    const onInputchangePK =(e) =>{
        setDK({...dangkykhoaluanPK, [e.target.name]: e.target.value});
    };


    const onInputchange =(e) =>{
        setDK({...dangkykhoaluan, [e.target.name]: e.target.value});
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/sinhvien/dangKyKL/${id}`, dangkykhoaluan);
        // navigate("/");
    };

  return (
    <div>
        <h2 className="text-center mt-5">ĐĂNG KÝ ĐỀ TÀI KHÓA LUẬN</h2>
        <hr></hr>
        <div className="form-group containe mt-5">
            <form onSubmit={(e) => onSubmit(e)}>
            <div className="row">
            <label className="col-md-2 form-label mt-2" htmlFor="sinhvien" >Mã sinh viên:</label>
            <input type="text"  className="col form-control"  placeholder="Nhập mã sinh viên" name='sinhvien' defaultValue={dangkykhoaluan.sinhvien.maSv} onChange={(e) => onInputchange(e)} />
            <label className="col-md-2 form-label mt-2" htmlFor="sinhvien2" >Mã sinh viên 2:</label>
            <input type="text"  className="col form-control"  placeholder="Nhập mã sinh viên 2 (nếu có)" name='maSv2' defaultValue={dangkykhoaluan.maSv2} onChange={(e) => onInputchange(e)} />
            <Link to={"/sinhvien/detai"} className='col-md-2 btn btn-primary ml-3'>Danh sách đề tài</Link>
            </div>
                
            <div className="form-outline mt-5 row">
                <div className='col-md-3'>
                <button type="submit" className="col btn btn-outline-primary btn-block mb-4">Đăng ký khóa luận</button>
                </div>
                <div className='col-md-3 text-center'>
                <button type="reset" className="col btn btn-danger btn-block mb-4 ml-3 text-center">Hủy</button>
                </div>
            </div>
            </form>
        </div>
    </div>
  )
}
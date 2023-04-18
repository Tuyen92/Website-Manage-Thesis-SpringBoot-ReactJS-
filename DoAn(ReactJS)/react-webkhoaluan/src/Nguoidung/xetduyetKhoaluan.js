import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';




export default function 
() {

    let navigate = useNavigate();

    const {id} = useParams();

    const [khoaluan, setkhoaluan]=useState({
        maGvhd:"",
        maGvpb:"",
        hoidongMaHd:""

    })

    const onInputchange =(e) =>{
        setkhoaluan({...khoaluan, [e.target.name]: e.target.value});
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/giaovu/xetDuyet/${id}`, khoaluan);
        navigate("/giaovu/dsDangky");
    };

    useEffect(() =>{
        loadkhoaluan();
      }, [])

    const loadkhoaluan = async(e) => {
        const result = await axios.get(`http://localhost:8080/giaovu/dsDangKy/${id}`);
        setkhoaluan(result.data);
    }

    const { maGvhd,maGvpb,hoidongMaHd} = khoaluan;

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Xét duyệt khóa luận của sinh viên </h2>
                <hr></hr>
                <form onSubmit={(e) => onSubmit(e)}>
                <div className='row mt-4'>
                    <label htmlFor='maGvhd' className='col-md-4 form-label mt-2' >Giảng viên hướng dẫn:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập giảng viên hướng dẫn" name='maGvhd' defaultValue={maGvhd} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='maGvpb' className='col-md-4 form-label mt-2' >Giảng viên phản biện:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập giảng viên phản biện" name='maGvpb' defaultValue={maGvpb} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='hoidongMaHd' className='col-md-4 form-label mt-2' >Mã hội đồng:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập mã hội đồng" name='hoidongMaHd' defaultValue={hoidongMaHd} onChange={(e) => onInputchange(e)} />
                </div>
                
                <button className='btn btn-outline-primary mt-4' type='submit' >Xét duyệt</button>
                <Link className='btn btn-danger mx-2 mt-4' to="/giaovu/dsDangky">Hủy</Link>
                </form>
            </div>
        </div>
    </div>
  )
}
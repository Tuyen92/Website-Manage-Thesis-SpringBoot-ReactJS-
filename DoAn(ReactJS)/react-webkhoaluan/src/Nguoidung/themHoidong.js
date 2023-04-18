import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';

export default function 
() {

    let navigate = useNavigate();
    const {id} = useParams();

    const [hoidong, setHoidong] = useState({
        maHd:"",
        tenHd:"",
    })

    const onInputchange =(e) =>{
        setHoidong({...hoidong, [e.target.name]: e.target.value});
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/giaovu/themHD`, hoidong);
        navigate("/giaovu/Hoidong");
    };

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Thêm mới hội đồng</h2>
                <hr></hr>

                <form onSubmit={(e) => onSubmit(e)}>
                
                    <label htmlFor='maHd' className=' form-label mt-2' >Mã hội đồng:</label>
                    <input className=' form-control' type={"number"} placeholder="Nhập mã hội đồng" name='maHd' defaultValue={hoidong.maHd} onChange={(e) => onInputchange(e)}  />
                    <label htmlFor='tenHd' className='form-label mt-2' >Tên hội đồng:</label>
                    <input className=' form-control' type={"text"} placeholder="Nhập tên hội đồng" name='tenHd' defaultValue={hoidong.tenHd} onChange={(e) => onInputchange(e)}  />
                    <button className='btn btn-outline-primary ml-3 mt-3' type='submit' >Tạo mới</button>
                
                </form>
            </div>
        </div>
    </div>
  )
}
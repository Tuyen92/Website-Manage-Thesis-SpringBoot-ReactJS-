import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';




export default function 
() {
    const {id} = useParams();

    const [tongketkhoaluan, settongketkhoaluan]=useState({
      maTk:"",
      diem:"",
      nam:"",
      ketQua:"",
      maNganh:"",
      maSv:""
    })

    


    useEffect(() =>{
        loadtongketkhoaluan();
      }, [])

    const loadtongketkhoaluan = async(e) => {
        const result = await axios.get(`http://localhost:8080/sinhvien/xemKetQua/${id}`);
        settongketkhoaluan(result.data);
    }


  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Xem điểm khóa luận tốt nghiệp của {tongketkhoaluan.maSv}</h2>

                {/* <label className="col form-label mt-2 ml-1">Điểm: {tongketkhoaluan.diem}</label>
                <label className="col form-label mt-2 ml-1">Điểm: {tongketkhoaluan.ketQua}</label>
                <label className="col form-label mt-2 ml-1">Điểm: {tongketkhoaluan.maNganh}</label>
                <label className="col form-label mt-2 ml-1">Điểm: {tongketkhoaluan.nam}</label> */}
                <div className='card mt-4 h-80' >
                  <div className='card-header'>
                    <ul className='list-group list-group-flush'>
                      <li className='list-group-item mt-2'>
                        <b>Điểm:</b>
                        {tongketkhoaluan.diem}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Kết quả: </b>
                        {tongketkhoaluan.ketQua}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Mã ngành: </b>
                        {tongketkhoaluan.maNganh}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Năm: </b>
                        {tongketkhoaluan.nam}
                      </li>
                      
                    </ul>
                  </div>
                  <Link className='btn btn-primary my-2 mt-4' to={"/"}>
                    Trở về trang chủ
                  </Link>
                </div>
            </div>
        </div>
    </div>
  )
}
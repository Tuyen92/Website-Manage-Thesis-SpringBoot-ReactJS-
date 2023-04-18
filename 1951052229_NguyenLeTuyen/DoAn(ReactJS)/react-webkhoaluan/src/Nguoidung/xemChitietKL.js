import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function 
() {
  const [khoaluans, setkhoaluans] = useState({
    khoaluanPK:{
      maKl:"",
      dangkykhoaluanMaDk:"",
      dangkykhoaluanDetaiMaDt:"",
      dangkykhoaluanSinhvienMaSv:""
    },
    maGvhd:"",
    maGvpb:"",
    nam:"",
    ghiChu:"",
    maSv2:""

  })
  
  const {id} = useParams();

  useEffect(() =>{
     dskhoaluan();
  }, [])

  const dskhoaluan = async () =>{
    const ketQua = await axios.get(`http://localhost:8080/giaovu/dsKhoaLuan/${id}`);
    setkhoaluans(ketQua.data);
  }


  return (
    <div className="container mt-4">
        <div className='py-4'>
        <h2>Chi tiết khóa luận của mã khóa luận: {khoaluans.khoaluanPK.maKl}</h2>
        <hr></hr>
        <div className='card mt-4 h-80' >
                  <div className='card-header'>
                    <ul className='list-group list-group-flush'>
                      <li className='list-group-item mt-2'>
                        <b>Mã giảng viên hướng dẫn:</b>
                        {khoaluans.maGvhd}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Mã giảng viên phản biện: </b>
                        {khoaluans.maGvpb}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Mã sinh viên: </b>
                        {khoaluans.khoaluanPK.dangkykhoaluanSinhvienMaSv}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Mã sinh viên(nếu có): </b>
                        {khoaluans.maSv2}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Mã đề tài: </b>
                        {khoaluans.khoaluanPK.dangkykhoaluanDetaiMaDt}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Năm: </b>
                        {khoaluans.nam}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Ghi chú: </b>
                        {khoaluans.ghiChu}
                      </li>
                      
                    </ul>
                  </div>
                  <Link className='btn btn-primary my-2 mt-4' to={"/giaovu/dsKhoaluan"}>
                    Trở về danh sách khóa luận
                  </Link>
                </div>
        </div>
    </div>
  )
}
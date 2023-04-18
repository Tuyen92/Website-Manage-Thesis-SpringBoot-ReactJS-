import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function 
() {
  const [hoidongs, sethoidongs] = useState({
    
        maHd: "",
        tenHd: "",
        ngayLap: "",
        tinhTranghd: ""
    

  })
  
  const {id} = useParams();

  useEffect(() =>{
     dshoidong();
  }, [])

  const dshoidong = async () =>{
    const ketQua = await axios.get(`http://localhost:8080/giaovu/dsHoiDong/${id}`);
    sethoidongs(ketQua.data);
  }


  return (
    <div className="container mt-4">
        <div className='py-4'>
        <h2>Chi tiết Hội đồng {hoidongs.maHd}</h2>
        <hr></hr>
        <div className='card mt-4 h-80' >
                  <div className='card-header'>
                    <ul className='list-group list-group-flush'>
                      <li className='list-group-item mt-2'>
                        <b>Mã hội đồng:</b>
                        {hoidongs.maHd}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Ngày lập: </b>
                        {hoidongs.ngayLap}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Tên hội đồng: </b>
                        {hoidongs.tenHd}
                      </li>
                      <li className='list-group-item mt-2'>
                        <b>Tình trạng: </b>
                        {hoidongs.tinhTranghd}
                      </li>
 
                    </ul>
                  </div>
                  <Link className='btn btn-primary my-2 mt-4' to={"/giaovu/Hoidong"}>
                    Trở về danh sách hội đồng
                  </Link>
                </div>
        </div>
    </div>
  )
}
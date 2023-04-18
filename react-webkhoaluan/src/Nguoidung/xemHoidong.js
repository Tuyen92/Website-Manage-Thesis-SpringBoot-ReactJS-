import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function 
() {
  const [hoidongs, sethoidongs] = useState([]);
  
  // const {id} = useParams();

  useEffect(() =>{
     dsHoidong();
  }, [])

  const dsHoidong = async () =>{
    const ketQua = await axios.get("http://localhost:8080/giaovu/dsHoiDong");
    sethoidongs(ketQua.data);
  }


  return (
    <div className="container mt-4">
        <div className='py-4'>
        <h2>Danh sách hội đồng khóa luận</h2>
        <hr></hr>
        <table className="table border mt-5">
            <thead>
                <tr>
                <th scope="col">STT</th>
                <th scope="col">ID - Tên Hội đồng</th>
                <th scope="col">Ngày lập</th>
                <th scope="col">Hoạt động</th>
                <th scope="col">Tác vụ</th>
                </tr>
            </thead>
            <tbody>
                {
                  hoidongs.map((hoidong, index) =>(
                    <tr>
                    <th scope="row" key={index}>{index+1}</th>
                    <td>{hoidong.maHd} - {hoidong.tenHd}</td>
                    <td>{hoidong.ngayLap}</td>
                    <td>{hoidong.tinhTranghd}</td>
                    <td>
                      <Link to={`/giaovu/HoiDong/${hoidong.maHd}`} className='btn btn-primary mx-2'>Chi tiết</Link>
                    </td>      
                    </tr>

                  ))
                }
                
            </tbody>
        </table>
        </div>
    </div>
  )
}
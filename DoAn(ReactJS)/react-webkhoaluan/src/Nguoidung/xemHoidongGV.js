import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function 
() {
  const [chitiethoidong, setCthd] = useState([]);
  
  const {id} = useParams();

  useEffect(() =>{
     dsHoidong();
  }, [])

  const dsHoidong = async () =>{
    const ketQua = await axios.get(`http://localhost:8080/giangvien/dsHoiDongGV/${id}`);
    setCthd(ketQua.data);
  }


  return (
    <div className="container mt-4">
        <div className='py-4'>
        <h2>Danh sách hội đồng khóa luận</h2>
        <hr></hr>
        <table className="table border mt-5">
            <thead>
                <tr>
                <th scope="col">ID - Tên Hội đồng</th>
                <th scope="col">Ngày lập</th>
                <th scope="col">Hoạt động</th>
                <th scope="col">Tác vụ</th>
                </tr>
            </thead>
            <tbody>
                {
                  chitiethoidong.map((chitiethoidong, index) =>(
                    <tr>
                    <td>{chitiethoidong.hoidong.maHd} - {chitiethoidong.hoidong.tenHd}</td>
                    <td>{chitiethoidong.hoidong.ngayLap}</td>
                    <td>{chitiethoidong.hoidong.tinhTranghd}</td>
                    <td>
                      <Link to={`/dsKhoaLuan/${chitiethoidong.hoidong.maHd}`} className='btn btn-primary mx-2'>DS khóa luận</Link>
                      <Link  className='btn btn-primary mx-2'>Chit tiết</Link>
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
import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function 
() {
  const [khoaluans, setkhoaluans] = useState([]);
  
  const {id} = useParams();

  useEffect(() =>{
     dskhoaluan();
  }, [])

  const dskhoaluan = async () =>{
    const ketQua = await axios.get(`http://localhost:8080/dsKhoaLuan/${id}`);
    setkhoaluans(ketQua.data);
  }


  return (
    <div className="container mt-4">
        <div className='py-4'>
        <h2>Danh sách khóa luận của hội đồng mã số {id}</h2>
        <hr></hr>
        <table className="table border mt-5">
            <thead>
                <tr>
                <th scope="col">Mã Khóa luận</th>
                <th scope="col">Sinh viên</th>
                <th scope="col">Mã đề tài</th>
                <th scope="col">Năm</th>
                <th scope="col">Tác vụ</th>
                </tr>
            </thead>
            <tbody>
                {
                  khoaluans.map((khoaluan, index) =>(
                    <tr>
                    <td>{khoaluan.khoaluanPK.maKl}</td>
                    <td>{khoaluan.khoaluanPK.dangkykhoaluanSinhvienMaSv}</td>
                    <td>{khoaluan.khoaluanPK.dangkykhoaluanDetaiMaDt}</td>
                    <td>{khoaluan.nam}</td>
                    <td>
                      <Link className='btn btn-primary mx-2' to={`/giaovu/dsKhoaluan/${khoaluan.khoaluanPK.maKl}`}>Chấm điểm</Link>
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
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
    const ketQua = await axios.get("http://localhost:8080/giaovu/dsKhoaLuan");
    setkhoaluans(ketQua.data);
  }


  return (
    <div className="container mt-4">
        <div className='py-4'>
        <h2>Danh sách khóa luận</h2>
        <hr></hr>
        <table className="table border mt-5">
            <thead>
                <tr>
                <th scope="col">STT</th>
                <th scope="col">ID - Mã Khóa luận</th>
                <th scope="col">Sinh viên 1:</th>
                <th scope="col">Sinh viên 2:</th>
                <th scope="col">Mã đề tài:</th>
                <th scope="col">Giảng viên hướng dẫn:</th>
                <th scope="col">Giảng viên phản biện:</th>
                </tr>
            </thead>
            <tbody>
                {
                  khoaluans.map((khoaluan, index) =>(
                    <tr>
                    <th scope="row" key={index}>{index+1}</th>
                    <td>{khoaluan.khoaluanPK.maKl}</td>
                    <td>{khoaluan.khoaluanPK.dangkykhoaluanSinhvienMaSv}</td>
                    <td>{khoaluan.maSv2}</td>
                    <td>{khoaluan.khoaluanPK.dangkykhoaluanDetaiMaDt}</td>
                    <td>{khoaluan.maGvhd}</td>
                    <td>{khoaluan.maGvpb}</td>
                    <td>
                      <Link className='btn btn-primary mx-2' to={`/giaovu/dsKhoaluan/${khoaluan.khoaluanPK.maKl}`}>Chi tiết</Link>
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
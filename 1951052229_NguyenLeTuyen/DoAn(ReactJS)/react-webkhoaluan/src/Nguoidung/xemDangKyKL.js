import React, {  useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function()
{

  const [DangkyKLs, setDangkyKLs] = useState([]);
  
  // const [id] = useParams();

  useEffect(() =>{
    dsDangkyKL();
  }, [])

  const dsDangkyKL = async () =>{
    const ketQua = await axios.get("http://localhost:8080/giaovu/dsDangKy");
    setDangkyKLs(ketQua.data);
  }

  // const xoaDangkyKL = async (id) => {
  //   await axios.delete(`http://localhost:8080/quantri/xoaND/${id}`)
  //   dsDangkyKL();
  // }


  return (
    <div className="container">
        <div className='py-4'>
        <h2>Danh sách đăng ký khóa luận</h2>
        <table className="table border mt-4">
            <thead>
                <tr>
                <th scope="col">STT</th>
                <th scope="col">Mã đăng ký</th>
                <th scope="col">Mã đề tài</th>
                <th scope="col">Mã sinh viên</th>
                <th scope="col">Xét duyệt</th>
                <th scope="col">Tác vụ</th>
                </tr>
            </thead>
            <tbody>
                {
                  DangkyKLs.map((DangkyKL, index) =>(
                    <tr>
                    <th scope="row" key={index}>{index+1}</th>
                    <td>{DangkyKL.dangkykhoaluanPK.maDk} </td>
                    <td>{DangkyKL.dangkykhoaluanPK.detaiMaDt}</td>
                    <td>{DangkyKL.dangkykhoaluanPK.sinhvienMaSv}</td>
                    <td>{DangkyKL.xetDuyet}</td>
                    <td>
                      <Link to={`/giaovu/dsDangKy/${DangkyKL.dangkykhoaluanPK.sinhvienMaSv}`} className='btn btn-primary mx-2'>Xét duyệt</Link>
                      {/* <button className='btn btn-danger mx-2' onClick={() => xoaDangkyKL(DangkyKL.DangkyKLPK.maNd)}>Xóa</button> */}
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
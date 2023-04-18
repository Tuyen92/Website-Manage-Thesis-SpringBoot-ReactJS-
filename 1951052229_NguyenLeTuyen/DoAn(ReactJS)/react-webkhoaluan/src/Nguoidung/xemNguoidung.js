import React, {  useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function()
{

  const [nguoidungs, setNguoidungs] = useState([]);
  
  const {maNd} = useParams();

  useEffect(() =>{
    dsNguoidung();
  }, [])

  const dsNguoidung = async () =>{
    const ketQua = await axios.get("http://localhost:8080/quantri/qlTaiKhoan");
    setNguoidungs(ketQua.data);
  }

  const xoaNguoidung = async (id) => {
    await axios.delete(`http://localhost:8080/quantri/xoaND/${maNd}`)
    dsNguoidung();
  }

  return (
    <div className="container">
        <div className='py-4'>
        <h2 class="mb-5">Danh sách các tài khoản người dùng</h2>
        <table className="table border">
            <thead>
                <tr>
                <th scope="col">STT</th>
                <th scope="col">ID - Họ Tên</th>
                <th scope="col">Username</th>
                <th scope="col">Hoạt động</th>
                <th scope="col">Chức vụ</th>
                <th scope="col">Tác vụ</th>
                </tr>
            </thead>
            <tbody>
                {
                  nguoidungs.map((nguoidung, index) =>(
                    <tr>
                    <th scope="row" key={index}>{index+1}</th>
                    <td>{nguoidung.nguoidungPK.maNd} - {nguoidung.ho} {nguoidung.ten}</td>
                    <td>{nguoidung.username}</td>
                    <td>{nguoidung.hoatDong}</td>
                    <td>{nguoidung.chucvu.tenCv}</td>
                    <td>
                      <Link to={`/quantri/suaNguoidung/${nguoidung.nguoidungPK.maNd}`} className='btn btn-primary mx-2'>Cập nhật</Link>
                      <button className='btn btn-danger mx-2' onClick={() => xoaNguoidung(nguoidung.nguoidungPK.maNd)}>Xóa</button>
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
import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useParams, Link } from 'react-router-dom';

export default function 
() {
  const [detais, setdetais] = useState([]);
  const [khoa, setKhoa] = useState([]);
  const {id} = useParams();

  useEffect(() =>{
     dsDetai();
  }, [])

  useEffect(() =>{
    dsKhoa();
 }, [])

  const dsDetai = async () =>{
    const ketQua = await axios.get("http://localhost:8080/dsDeTai");
    setdetais(ketQua.data);
  }

  const dsKhoa = async () =>{
    const ketQua = await axios.get("http://localhost:8080/khoa");
    setKhoa(ketQua.data);
  }


  return (
    <div className="container">
        <div className='py-4'>
        <h2 className='mb-5'>Danh sách đề tài khóa luận</h2>
        
        <div className='row'>
          <label className='col-md-2'>Xem đề tài theo khoa:</label>
          <select className='col-md-2'>
            {
              khoa.map((khoa, index) =>(
                <option>{khoa.tenKhoa}</option>
              ))
            }
          </select>
        </div>
       
        <table className="table border mt-4">
            <thead>
                <tr>
                <th scope="col">Mã đề tài</th>
                <th scope="col">Tên đề tài</th>
                <th scope="col">Nội dung</th>
                <th scope="col">Hạn nộp</th>
                <th scope="col">Khoa</th>
                <th scope="col">Tác vụ</th>
                </tr>
            </thead>
            <tbody>
                {
                  detais.map((detai, index) =>(
                    <tr>
                    <td>{detai.maDt}</td>
                    <td>{detai.tenDt}</td>
                    <td>{detai.noiDunngdt}</td>
                    <td>{detai.hanNop}</td>   
                    <td>{detai.khoaMaKhoa.tenKhoa}</td>        
                    <td>
                    <Link to={`/sinhvien/DangKyKhoaLuan/${detai.maDt}`} className='btn btn-primary mx-2'>Đăng ký</Link>
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
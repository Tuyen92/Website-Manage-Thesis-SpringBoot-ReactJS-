import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';

export default function 
() {
    let navigate = useNavigate();
    const {id} = useParams();

    const [chitiethoidong, setCthd] = useState({
        giangvien:{
            maGv:""
        },
        chucVuhd:""
    })

    const onInputchangeChiTiet =(e) =>{
        setCthd({...chitiethoidong, [e.target.name]: e.target.value});
    };

    const onSubmitChitiet = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/giaovu/phanCong/${id}`, chitiethoidong);
        navigate("/giaovu/Hoidong");
    };

return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Phân công giảng viên hội đồng</h2>
                <hr></hr>
                <form onSubmit={(e) => onSubmitChitiet(e)}>
                <div className='row'>
                    <label htmlFor='chitiethoidongPK' className='col-md-3 form-label mt-2'>Mã giảng viên:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập mã giảng viên" name='giangvien' defaultValue={chitiethoidong.giangvien.maGv} onChange={(e) => onInputchangeChiTiet(e)}  />
                    <label htmlFor='chucVuhd' className='col-md-2 form-label mt-2'>Chức vụ:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập chức vụ" name='chucVuhd' defaultValue={chitiethoidong.chucVuhd} onChange={(e) => onInputchangeChiTiet(e)}  />
                </div>

                <div className='row mt-4'>
                    <div className='"col-md-3 form-label ml-1"'>
                        <Link className='btn btn-danger' to="/quantri/QLTaiKhoan">Khóa / Mở khóa hội đồng</Link>
                    </div>
                </div>

                <button className='btn btn-outline-primary mt-4' type='submit' >Thêm thành viên</button>
                <Link className='btn btn-danger mx-2 mt-4' to="/quantri/QLTaiKhoan">Hủy</Link>
                </form>
            </div>
        </div>
    </div>
)
}
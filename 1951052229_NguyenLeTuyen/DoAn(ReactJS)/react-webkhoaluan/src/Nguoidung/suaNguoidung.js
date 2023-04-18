import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';





export default function 
() {

    

    let navigate = useNavigate();

    const {id} = useParams();

    const [nguoidung, setNguoidung]=useState({
        
        ho:"",
        ten:"",
        ngaySinh:"",
        anh : "",
        gioiTinh:"",
        diaChi:"",
        sdt:"",
        email:"",
        chucvu:{
            tenCv:""
        }
    })

    const onInputchange =(e) =>{
        setNguoidung({...nguoidung, [e.target.name]: e.target.value});
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/quantri/capnhatND/${id}`, nguoidung);
        navigate("/quantri/QLTaiKhoan");
    };

    useEffect(() =>{
        loadNguoidung();
      }, [])

    const loadNguoidung = async(e) => {
        const result = await axios.get(`http://localhost:8080/nguoidung/${id}`);
        setNguoidung(result.data);
    }

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Cập nhật thông tin tài khoản người dùng: {nguoidung.ho} {nguoidung.ten} </h2>
                <hr></hr>

                <form className='mt-5' onSubmit={(e) => onSubmit(e)}>
                <div className='row mt-4'>
                    <label htmlFor='ho' className='col-md-3 form-label mt-2 ml-1' >Họ Tên:</label>
                    <div className='col-md-5'>
                        <input className='col form-control' type={"text"} placeholder="Nhập họ người dùng" name='ho' defaultValue={nguoidung.ho} onChange={(e) => onInputchange(e)}  />
                    </div>
                    <div className='col-md-3'>
                        <input className='col form-control' type={"text"} placeholder="Nhập tên người dùng" name='ten' defaultValue={nguoidung.ten} onChange={(e) => onInputchange(e)} />
                    </div>
                </div>

                <div className="d-flex row mt-4">
                    <label htmlFor='gioiTinh' className="col-md-3 form-label mt-2 ml-1">Giới tính:</label>
                    <div className='col-md-5 ml-1'>
                        <FormControl className='ml-1'>
                            <RadioGroup row aria-labelledby="demo-row-radio-buttons-group-label" defaultValue= {nguoidung.gioiTinh} name="nguoidung.gioiTinh">
                                <FormControlLabel value="nam" control={<Radio />} label="Nam" />
                                <FormControlLabel value="nu" control={<Radio />} label="Nữ" />  
                            </RadioGroup>
                        </FormControl>  
                    </div>
                    <label className="col form-label mt-2 ml-1">Chức vụ: {nguoidung.chucvu.tenCv}</label>
                </div>
                
                {/* <form>
                    <div className="radio">
                    <label>
                        <input type="radio" value="nam" checked={true} />
                        Nam
                    </label>
                    </div>
                    <div className="radio">
                    <label>
                        <input type="radio" value="nu" />
                        Nữ
                    </label>
                    </div>
                    
                </form> */}
                

                <div className='row mt-4'>
                    <label htmlFor='ngaySinh' className="col-md-3 form-label mt-2 ml-1" >Ngày sinh:</label>
                    <input className='col form-control' type={"date"} placeholder="Nhập ngày sinh" name='ngaySinh' defaultValue={nguoidung.ngaySinh} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='diaChi' className="col-md-3 form-label mt-2 ml-1" >Địa chỉ:</label>
                    <input className='col form-control' type={"text"} placeholder="Địa chỉ" name='diaChi' defaultValue={nguoidung.diaChi} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='sdt' className="col-md-3 form-label ml-1" >Số điện thoại:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập số điện thoại" name='sdt' defaultValue={nguoidung.sdt} onChange={(e) => onInputchange(e)} />
                    <label htmlFor='email' className="col form-label mt-2" >Email:</label>
                    <input className='col ml-1 form-control' type={"text"} placeholder="Nhập email" name='email' defaultValue={nguoidung.email} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='anh' className="col-md-3 form-label mt-2 ml-1" >Ảnh đại diện:</label>
                    <div className="col">   
                        <input type="file" name='anh' defaultValue={nguoidung.anh} className="form-control"  />
                    </div>
                </div>

                <div className='row mt-4'>
                    <div className='"col-md-3 form-label ml-1"'>
                        <Link className='btn btn-danger' to="/quantri/QLTaiKhoan">Vô hiệu hóa / Hiệu lực hóa tài khoản</Link>
                    </div>
                </div>
                <button className='btn btn-outline-primary mt-4' type='submit' >Xác nhận</button>
                <Link className='btn btn-danger mx-2 mt-4' to="/quantri/QLTaiKhoan">Hủy</Link>
                </form>
            </div>
        </div>
    </div>
  )
}
import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { Radio, FormControl, RadioGroup,FormControlLabel } from '@material-ui/core';
import { data } from 'jquery';




export default function 
() {

    let navigate = useNavigate();

    const [nguoidung, setNguoidung] = useState({
        nguoidungPK:{
            maNd:"",
            chucvuMaCv:""
        },
        ho:"",
        ten:"",
        ngaySinh:"",
        anh : "",
        gioiTinh:"",
        diaChi:"",
        sdt:"",
        chucvu:{
            tenCv:""
        }
    })

    const[sinhvien, setSinhvien] = useState({
        nganh:{
            maNganh:"",
            khoaMaKhoa:""
        },
        nienKhoa:"",
        tinhTrang:""
    })

    const[giangvien, setGiangvien] = useState({
        hocVi:"",
        hocHam:""
    })

    const[giaovu, setGiaovu] = useState({
        phongBan:"",
    })

    const[quantri, setQuantri] = useState({
        congViec:"",
    })

    const onInputchange =(e) =>{
        setNguoidung({
            ...nguoidung,[e.target.name]: e.target.value
        });
       
    };

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post(`http://localhost:8080/quantri/themND`, nguoidung);
        navigate("/quantri/QLTaiKhoan");
    };

    // const {ho,ten,nguoidungPK:{maCv,maNd},} = nguoidung;

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
                <h2 className='text-center'>Cấp tài khoản người dùng mới</h2>
                <hr></hr>
                
                <form className='mt-4' onSubmit={(e) => onSubmit(e)}>
                <h4 className="text-left text-primary">Phần thông tin chung</h4>
                <div className='row mt-4'>
                    <label htmlFor='ho' className='col-md-1 form-label mt-2' >Họ:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập họ người dùng" name='ho' defaultValue={nguoidung.ho} onChange={(e) => onInputchange(e)}  />
                    <label htmlFor='ten' className='col-md-1 form-label mt-2' >Tên:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập tên người dùng" name='ten' defaultValue={nguoidung.ten} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='nguoidungPK.maNd' className='col-md-3 form-label mt-2' >Mã người dùng:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập mã người dùng" name='nguoidungPK.maNd' defaultValue={nguoidung.nguoidungPK.maNd} onChange={(e) => onInputchange(e)} />
                    <label htmlFor='nguoidungPK.maCv' className='col-md-2 form-label mt-2' >Chức vụ:</label>                        
                    <input className='col form-control' type={"text"} placeholder="Nhập chức vụ người dùng" name='nguoidungPK.chucvuMaCv' defaultValue={nguoidung.nguoidungPK.chucvuMaCv} onChange={(e) => onInputchange(e)} />
                </div>

                <div className="d-flex row mt-4">
                    <label className="col-md-2 form-label mt-2 ml-1">Giới tính:</label>
                    <div className='col-md-4 ml-1'>
                        <FormControl className='ml-1'>
                            <RadioGroup row aria-labelledby="demo-row-radio-buttons-group-label" defaultValue= {nguoidung.gioiTinh} name="row-radio-buttons-group">
                                <FormControlLabel value="male" control={<Radio />} label="Nam" />
                                <FormControlLabel value="female" control={<Radio />} label="Nữ" />  
                                
                            </RadioGroup>
                        </FormControl>
                    </div>
                    <label htmlFor='ngaySinh' className="col-md-2 form-label mt-2 ml-1" >Ngày sinh:</label>
                    <input className='col form-control' type={"date"} placeholder="Nhập ngày sinh" name='ngaySinh' defaultValue={nguoidung.ngaySinh} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='diaChi' className="col-md-3 form-label mt-2 ml-1" >Địa chỉ:</label>
                    <input className='col form-control' type={"text"} placeholder="Địa chỉ" name='diaChi' defaultValue={nguoidung.diaChi} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='sdt' className="col-md-3 form-label mt-2 ml-1" >Số điện thoại:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập số điện thoại" name='sdt' defaultValue={nguoidung.sdt} onChange={(e) => onInputchange(e)} />
                    <label htmlFor='sdt' className="col form-label mt-2" >Email:</label>
                    <input className='col ml-1 form-control' type={"text"} placeholder="Nhập email" name='sdt' defaultValue={nguoidung.email} onChange={(e) => onInputchange(e)} />
                </div>

                <div className='row mt-4'>
                    <label htmlFor='anh' className="col-md-3 form-label mt-2 ml-1" >Ảnh đại diện:</label>
                    <div className="col">   
                        <input type="file" name='anh' defaultValue={nguoidung.anh} className="form-control"  />
                    </div>
                </div>
                <hr></hr>
                <h4 className='text-left text-primary'>Phần thông tin riêng</h4>
                <h6 className='text-left text-primary mt-4'>- Sinh viên</h6>
                <div className='row mt-4'>
                    <label htmlFor='nganh' className='col-md-1 form-label mt-2' >Ngành:</label>
                    <input className='col form-control' type={"text"} placeholder="Chọn ngành" name='nganh' defaultValue={sinhvien.nganh} onChange={(e) => onInputchange(e)} />
                    <label htmlFor='khoa' className='col-md-1 form-label mt-2' >Khoa:</label>
                    <input className='col form-control' type={"text"} placeholder="Chọn khoa" name='khoa' defaultValue={sinhvien.nganh.khoaMaKhoa} onChange={(e) => onInputchange(e)} />
                </div>
                <div className='row mt-4'>
                    
                </div>
                <div className='row mt-4'>
                    <label htmlFor='nienKhoa' className='col form-label' >Niên khóa:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập niên khóa" name='nienKhoa' defaultValue={sinhvien.nienKhoa} onChange={(e) => onInputchange(e)} />
                </div>

                <h6 className='text-left text-primary mt-4'>- Giảng viên</h6>
                <div className='row mt-4'>
                    <label htmlFor='hocVi' className='col form-label' >Học vị:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập học vị" name='hocVi' defaultValue={giangvien.hocVi} onChange={(e) => onInputchange(e)} />
                </div>
                <div className='row mt-4'>
                    <label htmlFor='hocHam' className='col form-label' >Học hàm:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập học hàm" name='hocHam' defaultValue={giangvien.hocHam} onChange={(e) => onInputchange(e)} />
                </div>

                <h6 className='text-left text-primary mt-4'>- Giáo vụ</h6>
                <div className='row mt-4'>
                    <label htmlFor='phongBan' className='col form-label' >Phòng ban:</label>
                    <input className='col form-control' type={"text"} placeholder="Nhập phòng ban" name='phongBan' defaultValue={giaovu.phongBan} onChange={(e) => onInputchange(e)} />
                </div>

                <h6 className='text-left text-primary mt-4'>- Quản trị</h6>
                <div className='row mt-4'>
                    <label htmlFor='congViec' className='col form-label' >
                        Công việc:
                    </label>
                    <input className='col form-control' type={"text"} placeholder="Nhập công việc" name='congViec' defaultValue={quantri.congViec} onChange={(e) => onInputchange(e)} />
                </div>

                <button className='btn btn-outline-primary mt-4' type='submit' >
                    Xác nhận
                </button>
                <Link className='btn btn-danger mx-2 mt-4' to="/quantri/QLTaiKhoan">
                    Hủy
                </Link>
                </form>
            </div>
        </div>
    </div>
  )
}
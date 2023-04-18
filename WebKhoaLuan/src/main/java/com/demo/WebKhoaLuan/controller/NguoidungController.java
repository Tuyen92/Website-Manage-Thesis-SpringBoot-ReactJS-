/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.controller;
//
//import com.demo.WebKhoaLuan.config.JWTToken;
//import com.demo.WebKhoaLuan.login.LoginRequest;
//import com.demo.WebKhoaLuan.login.LoginResponse;
import com.demo.WebKhoaLuan.model.Giangvien;
import com.demo.WebKhoaLuan.model.Giaovu;
import com.demo.WebKhoaLuan.model.Nganh;
import com.demo.WebKhoaLuan.model.NganhPK;
import com.demo.WebKhoaLuan.model.Nguoidung;
import com.demo.WebKhoaLuan.model.Quantri;
import com.demo.WebKhoaLuan.model.Sinhvien;
import com.demo.WebKhoaLuan.repository.GiangvienRepository;
import com.demo.WebKhoaLuan.repository.GiaovuRepository;
import com.demo.WebKhoaLuan.repository.NguoidungRepository;
import com.demo.WebKhoaLuan.repository.QuantriRepository;
import com.demo.WebKhoaLuan.repository.SinhvienRepository;
import java.util.Calendar;
import java.util.List;
//import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NguoidungController {
    @Autowired
    private NguoidungRepository nguoidungRepository;
    @Autowired
    private SinhvienRepository sinhVienRepository;
    @Autowired
    private GiangvienRepository giangVienRepository;
    @Autowired
    private GiaovuRepository giaoVuRepository;
    @Autowired
    private QuantriRepository quanTriRepository;

    //ĐĂNG NHẬP
    @PostMapping("/dangNhap")
    public String dangNhap(@RequestBody Nguoidung nguoidung){
        try {
            Nguoidung nd = nguoidungRepository.layND(nguoidung.getUsername());
            if (nd.getPassword().equals(nguoidung.getPassword())) {
                return "Đăng nhập thành công";
            }
            else
                return "Nhập sai mật khẩu";
        } catch (Exception e) {
            return "Tài khoản không hiện có";
        }
    }
   
    //QUẢN TRỊ THÊM NGƯỜI DÙNG
    @PostMapping("/quantri/themND")
    public Nguoidung themNguoidung(@RequestBody Nguoidung nguoidung){
        nguoidung.setHoatDong(1);
        nguoidung.setUsername(nguoidung.getNguoidungPK().getMaNd());
        nguoidung.setPassword(nguoidung.getNguoidungPK().getMaNd());
        switch (nguoidung.getNguoidungPK().getChucvuMaCv()) {
            case "ROLE_QT":
                Quantri qt = new Quantri();
                qt.setNguoidung(nguoidung);
                qt.setCongViec("-");
                qt.setMaQt(nguoidung.getNguoidungPK().getMaNd());
                quanTriRepository.save(qt);
                break;
            case "ROLE_GVU":
                Giaovu gvu = new Giaovu();
                gvu.setNguoidung(nguoidung);
                gvu.setPhongBan("-");
                gvu.setMaGvu(nguoidung.getNguoidungPK().getMaNd());
                giaoVuRepository.save(gvu);
                break;
            case "ROLE_GV":
                Giangvien gv = new Giangvien();
                gv.setHocHam("-");
                gv.setHocVi("-");
                gv.setNguoidung(nguoidung);
                gv.setMaGv(nguoidung.getNguoidungPK().getMaNd());
                giangVienRepository.save(gv);
                break;
            case "ROLE_SV":
                Sinhvien sv = new Sinhvien();
                Nganh n = new Nganh();
                NganhPK nPK = new NganhPK();
                nPK.setKhoaMaKhoa("IT");
                nPK.setMaNganh("IT01");
                n.setNganhPK(nPK);
                sv.setNguoidung(nguoidung);
                sv.setMaSv(nguoidung.getNguoidungPK().getMaNd());
                sv.setTinhTrang(0);
                sv.setNienKhoa(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                sv.setNganh(n);
                sinhVienRepository.save(sv);
                break;
            default:
                throw new AssertionError();
        } 
        return nguoidungRepository.save(nguoidung);
    }
    
    //QUẢN TRỊ LẤY DANH SÁCH TÀI KHOẢN 
    @GetMapping("/quantri/qlTaiKhoan")
    public List<Nguoidung> layDSNguoidung(){
        return nguoidungRepository.findAll();
    }
    
    //QUẢN TRỊ LẤY THÔNG TIN TÀI KHOẢN MỘT NGƯỜI DÙNG
    @GetMapping("/quantri/qlTaiKhoan/{maNd}")
    public Nguoidung timNguoidung(@PathVariable(value = "maNd") String maNd){
        return nguoidungRepository.layND(maNd);
    }
    
    //NGƯỜI DÙNG XEM THÔNG TIN TÀI KHOẢN CỦA MÌNH
    @GetMapping("/nguoidung/{maNd}")
    public Nguoidung layNguoidung(@PathVariable(value = "maNd") String maNd){
        return nguoidungRepository.layND(maNd);
    }
    
    //QUẢN TRỊ LẤY DANH SÁCH THEO TỪNG CHỨC VỤ
    @GetMapping("/quantri/qlTaiKhoan/loai/{maCv}")
    public List<Nguoidung> layDSLoaiND(@PathVariable(value = "maCv") String maCv){
        return nguoidungRepository.layLoaiND(maCv);
    }
    
    //QUẢN TRỊ XÓA TÀI KHOẢN
    @DeleteMapping("/quantri/xoaND/{maNd}")
    public String xoaNguoidung(@PathVariable(value = "maNd") String maNd){
        Nguoidung nd = nguoidungRepository.layND(maNd);
        try {
            switch (nd.getNguoidungPK().getChucvuMaCv()) {
                case "ROLE_QT":
                    quanTriRepository.deleteById(maNd);
                    break;
                case "ROLE_GVU":
                    giaoVuRepository.deleteById(maNd);
                    break;
                case "ROLE_GV":
                    giangVienRepository.deleteById(maNd);
                    break;
                case "ROLE_SV":
                    sinhVienRepository.deleteById(maNd);
                    break;
            }
            nguoidungRepository.deleteByMaNd(maNd);
        } catch (Exception e) {
            return "Xóa người dùng không thành công";
        }
        return "Đã xóa người dùng thành công";
    }
    
    //QUẢN TRỊ CẬP NHẬT THÔNG TIN TÀI KHOẢN NGƯỜI DÙNG
    @PostMapping("/quantri/capnhatND/{maNd}")
    public String capNhatND(@PathVariable (value = "maNd") String maNd, @RequestBody Nguoidung nguoidung) {
        Nguoidung nd = nguoidungRepository.layND(maNd);
        if (nguoidung.getHo() != null)
            nd.setHo(nguoidung.getHo());
        if (nguoidung.getTen() != null)
            nd.setTen(nguoidung.getTen());
        if (nguoidung.getNgaySinh() != null)
            nd.setNgaySinh(nguoidung.getNgaySinh());
        if (nguoidung.getDiaChi()!= null)
            nd.setDiaChi(nguoidung.getDiaChi());
        if (nguoidung.getEmail() != null)
            nd.setEmail(nguoidung.getEmail());
        if (nguoidung.getGioiTinh() != null)
            nd.setGioiTinh(nguoidung.getGioiTinh());
        if (nguoidung.getSdt() != null)
            nd.setSdt(nguoidung.getSdt());            
        try {
            nguoidungRepository.save(nd);
        } catch (Exception e) {
            return "Cập nhật người dùng không thành công";
        }
        return "Cập nhật người dùng thành công";
    }
    
    //NGƯỜI DÙNG CẬP NHẬT THÔNG TIN CÁC NHÂN
    @PostMapping("/capnhatND/{maNd}")
    public String capNhatNDCaNhan(@PathVariable(value = "maNd") String maNd, @RequestBody Nguoidung nguoidung){
        Nguoidung nd = nguoidungRepository.layND(maNd);
        String message = "";
        if (nguoidung.getNgaySinh() != null)
            nd.setNgaySinh(nguoidung.getNgaySinh());
        if (nguoidung.getDiaChi() != null)
            nd.setDiaChi(nguoidung.getDiaChi());
        if (nguoidung.getEmail() != null)
            nd.setEmail(nguoidung.getEmail());
        if (nguoidung.getSdt() != null)
            nd.setSdt(nguoidung.getSdt());
        if (nguoidung.getPassword() != null) {
            if(nguoidung.getPassword().equals(nguoidung.getConfirmPass()))
                nd.setPassword(nguoidung.getConfirmPass());
            else{
                message = "Nhập sai mật khẩu mới";
                return message;
            }  
        }
        try {
            nguoidungRepository.save(nd);
        } catch (Exception e) {
           message = "Cập nhật người dùng không thành công";
           return message;
        }
        message = "Cập nhật người dùng thành công";
        return message;
    }
    
    //QUẢN TRỊ CẬP NHẬT TÌNH TRẠNG TÀI KHOẢN
    @PostMapping("/quantri/tinhTrangTK/{maNd}")
    public String capNhatTinhTrang(@PathVariable(value = "maNd") String maNd){
        Nguoidung nd = nguoidungRepository.layND(maNd);
        if (nd.getHoatDong() == 1)
            nd.setHoatDong(0);
        else
            nd.setHoatDong(1);
        try {
            nguoidungRepository.save(nd);
            return "Đã cập nhật thành công";
        } catch (Exception e) {
            return "Cập nhật không thành công";
        }   
    }
    
    //QUẢN TRỊ XEM DANH SÁCH SINH VIÊN THEO KHOA
    @GetMapping("/quantri/dsSVKhoa/{maKhoa}")
    public List<Sinhvien> dsSinhVienKhoa(@PathVariable(value = "maKhoa") String maKhoa){
        return sinhVienRepository.laySVKhoa(maKhoa);
    }
    
    //QUẢN TRỊ XEM DANH SÁCH SINH VIÊN THEO NGÀNH
    @GetMapping("/quantri/dsSVNganh/{maNganh}")
    public List<Sinhvien> dsSinhVienNganh(@PathVariable(value = "maNganh") String maNganh){
        return sinhVienRepository.laySVNganh(maNganh);
    }
    
    //QUẢN TRỊ XEM DANH SÁCH TÀI KHOẢN BỊ VÔ HIỆU HÓA
    @GetMapping("/quantri/dsTaiKhoanVHH")
    public List<Nguoidung> dsTaiKhoanVHH(){
        return nguoidungRepository.layDSHoatDong(0);
    }
    
    //QUẢN TRỊ XEM DANH SÁCH TÀI KHOẢN ĐANG HIỆU LỰC
    @GetMapping("/quantri/dsTaiKhoanHLH")
    public List<Nguoidung> dsTaiKhoanHLH(){
        return nguoidungRepository.layDSHoatDong(1);
    }
    
    //QUẢN TRỊ CẬP NHẬT TÌNH TRẠNG SINH VIÊN
    @PostMapping("/quantri/capNhatSV/{maSv}")
    public String capNhatSV(@PathVariable(value = "maSv") String maSv){
        Sinhvien s = sinhVienRepository.laySinhvien(maSv);
        try {
            if (s.getTinhTrang() == 0)
                s.setTinhTrang(1);
            else
                s.setTinhTrang(0);
            sinhVienRepository.save(s);
        } catch (Exception e) {
            return "Cập nhật không thành công";
        }
        return "Đã cập nhật thành công";
    }
    
    //QUẢN TRỊ CẬP NHẬT TÌNH TRẠNG HOẠT ĐỘNG TÀI KHOẢN
    @PostMapping("/quantri/capNhatTK/{maNd}")
    public String capNhatTT(@PathVariable(value = "maSv") String maNd){
        Nguoidung nd = nguoidungRepository.layND(maNd);
        String message = "";
        try {
            if (nd.getHoatDong() == 0){
                nd.setHoatDong(1);
                message = "Đã hiệu lực hóa tài khoản";
            }
            else{
                nd.setHoatDong(0);
                message = "Đã vô hiệu hóa tài khoản";
            }    
            nguoidungRepository.save(nd);
        } catch (Exception e) {
            message = "Cập nhật không thành công";
            return message;
        }
        return message;
    }
}

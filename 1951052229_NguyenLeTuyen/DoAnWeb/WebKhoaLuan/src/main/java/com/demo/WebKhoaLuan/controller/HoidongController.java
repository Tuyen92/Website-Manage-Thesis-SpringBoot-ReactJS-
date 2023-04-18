/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.controller;

import com.demo.WebKhoaLuan.model.Chitiethoidong;
import com.demo.WebKhoaLuan.model.ChitiethoidongPK;
import com.demo.WebKhoaLuan.model.Giangvien;
import com.demo.WebKhoaLuan.model.Hoidong;
import com.demo.WebKhoaLuan.model.Khoaluan;
import com.demo.WebKhoaLuan.repository.ChitiethoidongRepository;
import com.demo.WebKhoaLuan.repository.GiangvienRepository;
import com.demo.WebKhoaLuan.repository.HoidongRepository;
import com.demo.WebKhoaLuan.repository.KhoaluanRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HoidongController {
    @Autowired
    private HoidongRepository hoiDongRepository;
    @Autowired
    private ChitiethoidongRepository chiTietHoiDongRepository;
    @Autowired
    private KhoaluanRepository khoaLuanRepository;
    @Autowired
    private GiangvienRepository giangVienReposirtory;
    
    //GIÁO VỤ LẤY DANH SÁCH HỘI ĐỒNG
    @GetMapping("/giaovu/dsHoiDong")
    public List<Hoidong> dsHoiDong(){
        return hoiDongRepository.findAll();
    }
    
    //GIÁO VỤ XEM MỘT HỘI ĐỒNG
    @GetMapping("/giaovu/dsHoiDong/{maHd}")
    public Hoidong layHoiDong(@PathVariable(value = "maHd") int maHd){
        return hoiDongRepository.layHD(maHd);
    }
    
    //GIÁO VỤ LẤY DANH SÁCH HỘI ĐỒNG HOẠT ĐỘNG 
    @GetMapping("/giaovu/dsHDHoatDong")
    public List<Hoidong> dsHDHoatDong(){
        return hoiDongRepository.dsHDHoatDong(Short.parseShort("1"));
    }
    
    //GIÁO VỤ LẤY DANH SÁCH HĐ ĐỒNG HẾT HOẠT ĐỘNG
    @GetMapping("/giaovu/dsHDKhongHoatDong")
    public List<Hoidong> dsHDKHoatDong(){
        return hoiDongRepository.dsHDHoatDong(Short.parseShort("0"));
    }
    
    //GIẢNG VIÊN LẤY DANH SÁCH HỘI ĐỒNG GIANG VIÊN THAM GIA
    @GetMapping("/giangvien/dsHoiDongGV/{maGv}")
    public List<Chitiethoidong> dsHDGV(@PathVariable(value = "maGv") String maGv){
        return hoiDongRepository.layHDGV(maGv);
    }
    
    //GIÁO VỤ CẬP NHẬT TÌNH TRẠNG CỦA HỘI ĐỒNG
    @PostMapping("/giaovu/tinhTrangHD/{maHd}")
    public String tinhTrangHD(@PathVariable(value = "maHd") int maHd){
        Hoidong hd = hoiDongRepository.layHD(maHd);
        if (hd.getTinhTranghd() == 1)
            hd.setTinhTranghd(0);
        else
            hd.setTinhTranghd(1);
        try {
            hoiDongRepository.save(hd);
        } catch (Exception e) {
            return "Cập nhật không thành công";
        }
        return "Cập nhật thành công";
    }
    
    //GIÁO VỤ THÊM HỘI ĐỒNG MỚI
    @PostMapping("/giaovu/themHD")
    public String themHD(@RequestBody Hoidong hoidong){
        hoidong.setNgayLap(Calendar.getInstance().getTime());
        hoidong.setTinhTranghd(1);
        try {
            hoiDongRepository.save(hoidong);
        } catch (Exception e) {
            return "Thêm hội đồng không thành công";
        }
        return "Thêm hội đồng thành công";
    }
    
    //GIÁO VỤ PHÂN CÔNG GIẢNG VIÊN CHO HỘI ĐỒNG 
    @PostMapping("/giaovu/phanCong/{maHd}")
    public String phanCong(@PathVariable(value = "maHd") int maHd, @RequestBody Chitiethoidong chitiethoidong){
        if (kiemSLThanhVien(maHd) < 5){
            Hoidong hd = hoiDongRepository.layHD(maHd);
            ChitiethoidongPK cthdPK = new ChitiethoidongPK();
            cthdPK.setHoidongMaHd(hd.getMaHd());
            cthdPK.setGiangvienMaGv(chitiethoidong.getGiangvien().getMaGv());
            chitiethoidong.setChitiethoidongPK(cthdPK);
            chitiethoidong.setHoidong(hd);            
            try {
                chiTietHoiDongRepository.save(chitiethoidong);
            } catch (Exception e) {
                return "Phân công giảng viên không thành công";
            }
            return "Phân công giảng viên thành công";
        }
        return "Hội đồng đã đủ thành viên, không thể gán thêm";
    }
    
    //GIÁO VỤ XÓA HỘI ĐỒNG
    @DeleteMapping("/giaovu/xoaHD/{maHd}")
    public String xoaHD(@PathVariable(value = "maHd") int maHd){
        if (kiemSLKhoaLuan(maHd) == 0) {
            try {
                hoiDongRepository.deleteById(maHd);
            } catch (Exception e) {
                return "Xóa hội đồng không thành công";
            }
            return "Xóa hội đồng thành công";
        }
        return "Không được phép xóa hội đồng đã có khóa luận";
    }

    //KIỂM TRA SỐ LƯỢNG THÀNH VIÊN MỘT HỘI ĐỒNG
    public int kiemSLThanhVien(int maHd){
        List<Chitiethoidong> ds = hoiDongRepository.kiemSLTV(maHd);
        return ds.size();
    }
    
    //KIỂM TRA SỐ LƯỢNG KHÓA LUẬN MỘT HỘI ĐỒNG
    public int kiemSLKhoaLuan(int maHd){
        List<Khoaluan> dsKL = khoaLuanRepository.layDsKLHD(maHd);
        return dsKL.size();
    }
}

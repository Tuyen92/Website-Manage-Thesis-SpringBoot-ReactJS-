/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.controller;

import com.demo.WebKhoaLuan.model.Dangkykhoaluan;
import com.demo.WebKhoaLuan.model.Khoaluan;
import com.demo.WebKhoaLuan.model.Tongketkhoaluan;
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
public class KhoaluanController {
    @Autowired
    private KhoaluanRepository khoaLuanRepository;
    
    //GIÁO VỤ LẤY DANH SÁCH KHÓA LUẬN
    @GetMapping("/giaovu/dsKhoaLuan")
    public List<Khoaluan> dsKhoaLuan(){
        return khoaLuanRepository.findAll();
    }
    
    //GIÁO VỤ LẤY MỘT KHÓA LUẬN
    @GetMapping("/giaovu/dsKhoaLuan/{maKl}")
    public Khoaluan layKhoaLuan(@PathVariable(value = "maKl") int maKl){
        return khoaLuanRepository.layKhoaLuan(maKl);
    }
    
    //GIẢNG VIÊN LẤY DANH SÁCH HƯỚNG DẪN
    @GetMapping("/giangvien/dsKLHuongDan/{maGv}")
    public List<Khoaluan> dsKLHD(@PathVariable(value = "maGv") String maGv){
        return khoaLuanRepository.layKLGV(maGv);
    }
    
    //DANH SÁCH KHÓA LUẬN CỦA MỘT HỘI ĐỒNG
    @GetMapping("/dsKhoaLuan/{maHd}")
    public List<Khoaluan> dsKLHoiDong(@PathVariable(value = "maHd") int maHd){
        return khoaLuanRepository.layDsKLHD(maHd);
    }
    
    //GIẢNG VIÊN LẤY MỘT KHÓA LUẬN
    @GetMapping("/giangvien/dsKhoaLuan/{maKl}")
    public Khoaluan dsKLGV(@PathVariable(value = "maKl") int maKl){
        return khoaLuanRepository.layKhoaLuan(maKl);
    }
    
    //GIÁO VỤ XÓA MỘT KHÓA LUẬN
    @DeleteMapping("/giaovu/xoaKhoaLuan/{maKl}")
    public String xoaKhoaLuan(@PathVariable(value = "maKl") int maKl){
        Khoaluan kl = khoaLuanRepository.layKhoaLuan(maKl);
        try {
            khoaLuanRepository.deleteById(kl.getKhoaluanPK());
        } catch (Exception e) {
            return "Xóa khóa luận không thành công";
        }
       return "Xóa khóa luận thành công";
    }
    
    //SINH VIÊN NỘP KHÓA LUẬN
    @PostMapping("/sinhvien/nopKhoaLuan/{maKl}")
    public String nopKhoaLuan(@PathVariable(value = "maKl") int maKl, @RequestBody Khoaluan khoaluan){
        Khoaluan kl = khoaLuanRepository.layKhoaLuan(maKl);
        kl.setFileNop(khoaluan.getFileNop());
        kl.setGhiChu(khoaluan.getGhiChu());
        kl.setNgayNop(Calendar.getInstance().getTime());
        if (khoaluan.getGhiChu() != null)
            kl.setGhiChu(khoaluan.getGhiChu());
        try {
            khoaLuanRepository.save(kl);
        } catch (Exception e) {
            return "Nộp khóa luận không thành công";
        }
        return "Đã nộp khóa luận thành công";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.controller;

import com.demo.WebKhoaLuan.model.Dangkykhoaluan;
import com.demo.WebKhoaLuan.model.DangkykhoaluanPK;
import com.demo.WebKhoaLuan.model.Khoaluan;
import com.demo.WebKhoaLuan.model.KhoaluanPK;
import com.demo.WebKhoaLuan.repository.DangkyRepository;
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
public class DangkyController {
    @Autowired
    private DangkyRepository dangkyRepository;
    @Autowired
    private KhoaluanRepository khoaluanRepository;
    
    //GIÁO VỤ LẤY DANH SÁCH ĐĂNG KÝ KHÓA LUẬN
    @GetMapping("/giaovu/dsDangKy")
    public List<Dangkykhoaluan> dsDangKy(){
        return dangkyRepository.findAll();
    }
    
    //GIÁO VỤ LẤY DANH SÁCH ĐĂNG KÝ KHÓA LUẬN ĐÃ XÉT DUYỆT
    @GetMapping("/giaovu/dsDKDaXet")
    public List<Dangkykhoaluan> dsDKXetDuyet(){
        return dangkyRepository.dsDKXetDuyet(1);
    }
    
    //GIÁO VỤ LẤY DANH SÁCH ĐĂNG KÝ KHÓA LUẬN CHƯA XÉT DUYỆT
    @GetMapping("/giaovu/dsDKChuaDaXet")
    public List<Dangkykhoaluan> dsDKChuaXetDuyet(){
        return dangkyRepository.dsDKXetDuyet(0);
    }
    
    //GIÁO VỤ LẤY ĐĂNG KÝ KHÓA LUẬN CỦA SINH VIÊN
    @GetMapping("/giaovu/dsDangKy/{maSv}")
    public Dangkykhoaluan layDangKy(@PathVariable(value = "maSv") String maSv){
        return dangkyRepository.layDKSV(maSv);
    }
    
    //GIÁO VỤ XÓA ĐĂNG KÝ KHÓA LUẬN CHƯA XÉT DUYỆT
    @DeleteMapping("/giaovu/xoaDangKy/{maDk}")
    public String xoaDangKy(@PathVariable(value = "maDk") int maDk){
        Dangkykhoaluan dk = dangkyRepository.layDK(maDk);
        if (dk.getXetDuyet() == 1)
            return "Không cho xóa";
        else
            dangkyRepository.deleteById(dk.getDangkykhoaluanPK());
        return "Xóa thành công";
    }
    
    //SINH VIÊN ĐĂNG KÝ LÀM KHÓA LUẬN
    @PostMapping("/sinhvien/dangKyKL")
    public String themDK(@RequestBody Dangkykhoaluan dangkykhoaluan){  
        dangkykhoaluan.setXetDuyet(0);
        dangkykhoaluan.setNgayDk(Calendar.getInstance().getTime());
        try {
            dangkyRepository.save(dangkykhoaluan);
        } catch (Exception e) {
            return "Đăng ký không thành công";
        }
        return "Đăng ký thành công";
    }
    
    @PostMapping("/sinhvien/dangKyKL/{maDt}")
    public String themDKKL(@PathVariable(value = "maDt") int maDt, @RequestBody Dangkykhoaluan dangkykhoaluan){  
        DangkykhoaluanPK dkPK = new DangkykhoaluanPK();
        dkPK.setDetaiMaDt(maDt);
        dkPK.setSinhvienMaSv(dangkykhoaluan.getSinhvien().getMaSv());
        dangkykhoaluan.setXetDuyet(0);
        dangkykhoaluan.setNgayDk(Calendar.getInstance().getTime());
        dangkykhoaluan.setDangkykhoaluanPK(dkPK);
        try {
            dangkyRepository.save(dangkykhoaluan);
        } catch (Exception e) {
            return "Đăng ký không thành công";
        }
        return "Đăng ký thành công";
    }
    
    //SINH VIÊN XEM ĐĂNG KÝ KHÓA LUẬN CỦA MÌNH
    @GetMapping("/sinhvien/xemDKKL/{maSv}")
    public Dangkykhoaluan xemDKKL(@PathVariable(value = "maSv") String maSv){
        return dangkyRepository.layDKSV(maSv);
    }
    
    //GIÁO VỤ XÉT DUYỆT MỘT ĐĂNG KÝ & GÁN GIẢNG VIÊN, HỘI ĐỒNG
    @PostMapping("/giaovu/xetDuyet/{maDk}")
    public String xetDuyetDK(@PathVariable(value = "maDk") int maDk, @RequestBody Khoaluan khoaluan){
        Dangkykhoaluan dk = dangkyRepository.layDK(maDk);
        KhoaluanPK klPK = new KhoaluanPK();
        klPK.setMaKl(maDk);
        klPK.setDangkykhoaluanMaDk(maDk);
        klPK.setDangkykhoaluanSinhvienMaSv(dk.getSinhvien().getMaSv());
        klPK.setDangkykhoaluanDetaiMaDt(dk.getDetai().getMaDt());
        dk.setXetDuyet(1);
        if (khoaluanRepository.demSoLuongKL(khoaluan.getHoidongMaHd().getMaHd()) < 5) {
            khoaluan.setDangkykhoaluan(dk);
            khoaluan.setNam(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
            khoaluan.setMaSv2(dk.getMaSv2());
            khoaluan.setKhoaluanPK(klPK);
            try {
                dangkyRepository.save(dk);
                khoaluanRepository.save(khoaluan);
            } catch (Exception e) {
                return "Xét duyệt khóa luận không thành công";
            }
            return "Xét duyệt khóa luận thành công";
        }
        return "Hội đồng đã đạt tối đa số lượng khóa luận";
    }
}

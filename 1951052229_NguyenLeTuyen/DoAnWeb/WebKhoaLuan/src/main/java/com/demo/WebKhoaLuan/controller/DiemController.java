/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.controller;

import com.demo.WebKhoaLuan.model.Detai;
import com.demo.WebKhoaLuan.model.Diem;
import com.demo.WebKhoaLuan.model.DiemPK;
import com.demo.WebKhoaLuan.model.Khoaluan;
import com.demo.WebKhoaLuan.model.Tongketkhoaluan;
import com.demo.WebKhoaLuan.repository.DetaiRepository;
import com.demo.WebKhoaLuan.repository.DiemRepository;
import com.demo.WebKhoaLuan.repository.KhoaluanRepository;
import com.demo.WebKhoaLuan.repository.TongketRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DiemController {
    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    private TongketRepository tongKetRepository;
    @Autowired
    private KhoaluanRepository khoaLuanRepository;
    @Autowired
    private DetaiRepository deTaiRepository;
   
    //GIẢNG VIÊN CHẤM ĐIỂM KHÓA LUẬN
    @PostMapping("/giangvien/chamDiem/{maKl}")
    public String chamDiem(@PathVariable(value = "maKl") int maKl, @RequestBody Diem diem){
        Khoaluan kl = khoaLuanRepository.layKhoaLuan(maKl);
        DiemPK diemPK = new DiemPK();
        diemPK.setTieuchiMaTc("TC1");
        diemPK.setKhoaluanMaKl(maKl);
        diemPK.setKhoaluanDangkykhoaluanSinhvienMaSv(kl.getKhoaluanPK().getDangkykhoaluanSinhvienMaSv());
        diemPK.setKhoaluanDangkykhoaluanDetaiMaDt(kl.getKhoaluanPK().getDangkykhoaluanDetaiMaDt());
        diemPK.setKhoaluanDangkykhoaluanMaDk(kl.getKhoaluanPK().getDangkykhoaluanMaDk());
        diemPK.setGiangvienMaGv(diem.getGiangvien().getMaGv());
        diem.setDiemPK(diemPK);
        try {
            diemRepository.save(diem);
        } catch (Exception e) {
            return "Chấm điểm không thành công";
        }
        return "Đã lưu thông tin điểm";
    }
    
    //GIẢNG VIÊN CHẤM ĐIỂM KHÓA LUẬN THEO HD
    @PostMapping("/giangvien/chamDiemHD/{maKl}")
    public String chamDiemHD(@PathVariable(value = "maKl") int maKl, @RequestBody Diem diem){
        Khoaluan kl = khoaLuanRepository.layKhoaLuan(maKl);
        DiemPK diemPK = new DiemPK();
        diemPK.setTieuchiMaTc("TC2");
        diemPK.setKhoaluanMaKl(maKl);
        diemPK.setKhoaluanDangkykhoaluanSinhvienMaSv(kl.getKhoaluanPK().getDangkykhoaluanSinhvienMaSv());
        diemPK.setKhoaluanDangkykhoaluanDetaiMaDt(kl.getKhoaluanPK().getDangkykhoaluanDetaiMaDt());
        diemPK.setKhoaluanDangkykhoaluanMaDk(kl.getKhoaluanPK().getDangkykhoaluanMaDk());
        diemPK.setGiangvienMaGv(diem.getGiangvien().getMaGv());
        diem.setDiemPK(diemPK);
        try {
            diemRepository.save(diem);
        } catch (Exception e) {
            return "Chấm điểm không thành công";
        }
        return "Đã lưu thông tin điểm";
    }
    
    //SINH VIÊN XEM ĐIỂM KHÓA LUẬN
    @GetMapping("/sinhvien/xemKetQua/{maSv}")
    public Tongketkhoaluan xemKQ(@PathVariable(value = "maSv") String maSv){
        return tongKetRepository.layTongKet(maSv);
    }
    
    //LƯU THÔNG TIN ĐIỂM KHÓA LUẬN
    @PostMapping("/sinhvien/xemKQ/{maSv}")
    public Tongketkhoaluan xemKetQua(@PathVariable(value = "maSv") String maSv){
        Khoaluan kl = khoaLuanRepository.layKhoaluanSV(maSv);
        List<Diem> diemGV = diemRepository.layDiemGVKL(kl.getKhoaluanPK().getMaKl());
        List<Diem> diemHD = diemRepository.layDiemHDKL(kl.getKhoaluanPK().getMaKl());
        Detai dt = deTaiRepository.layDeTai(kl.getDangkykhoaluan().getDetai().getMaDt());
        double diemTong = 0;
        String result;
        if (kl.getNgayNop().before(dt.getHanNop())) {
            diemTong = (tinhTBC(diemGV) * 0.4) + (tinhTBC(diemHD) * 0.6);
            result = xetKetQua(diemTong);
            Tongketkhoaluan tk = new Tongketkhoaluan();
            tk.setMaSv(maSv);
            tk.setDiem(BigDecimal.valueOf(diemTong));
            tk.setMaNganh(kl.getDangkykhoaluan().getSinhvien().getNganh().getNganhPK().getKhoaMaKhoa());
            tk.setNam(kl.getNam());
            tk.setKetQua(result);
            return tongKetRepository.save(tk);
        }
        else {
            result = "F";
            Tongketkhoaluan tk = new Tongketkhoaluan();
            tk.setMaSv(maSv);
            tk.setDiem(BigDecimal.valueOf(diemTong));
            tk.setKetQua(result);
            return tongKetRepository.save(tk);
        }
    }
    
    //TÍNH ĐIỂM TRUNG BÌNH
    public double tinhTBC(List<Diem> diem){
        double diemTBC = 0;
        for (int i = 0; i < diem.size(); i++){
            diemTBC += Double.parseDouble(String.valueOf(diem.get(i).getDiem()));
        }
        diemTBC = diemTBC / Double.parseDouble(String.valueOf(diem.size()));
        return diemTBC;
    }

    //XEM KẾT QUẢ KHÓA LUẬN
    public String xetKetQua(double score) {
        if (score <= 10 && score >= 8.5) {
            return "A";
        }
        if (score < 8.5 && score >= 8) {
            return "B+";
        }
        if (score < 8 && score >= 7) {
            return "B";
        }
        if (score < 7 && score >= 6.5) {
            return "C+";
        }
        if (score < 6.5 && score >= 5.5) {
            return "C";
        }
        if (score < 5.5 && score >= 5) {
            return "D+";
        }
        if (score < 5 &&score >= 4) {
            return "D";
        }
        return "F";
    }
}

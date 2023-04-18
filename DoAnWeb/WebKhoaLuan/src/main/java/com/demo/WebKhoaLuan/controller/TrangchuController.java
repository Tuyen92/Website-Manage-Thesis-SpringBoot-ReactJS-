/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.controller;

import com.demo.WebKhoaLuan.model.Chucvu;
import com.demo.WebKhoaLuan.model.Khoa;
import com.demo.WebKhoaLuan.model.Nganh;
import com.demo.WebKhoaLuan.repository.ChucvuRepository;
import com.demo.WebKhoaLuan.repository.KhoaRepository;
import com.demo.WebKhoaLuan.repository.NganhRepository;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
public class TrangchuController {
    @Autowired
    private KhoaRepository khoaRepository;
    @Autowired
    private NganhRepository nganhRepository;
    @Autowired
    private ChucvuRepository chucVuRepository;
    
    //TRANG CHỦ
    @GetMapping
    public String home(){
        return "Hello user";
    }
    
    //DANH SÁCH KHOA
    @GetMapping("/khoa")
    public List<Khoa> DSKhoa() {
        return khoaRepository.findAll();
    }
    
    //DANH SÁCH NGÀNH
    @GetMapping("/nganh")
    public List<Nganh> DSNganh() {
        return nganhRepository.findAll();
    }
    
    //DANH SÁCH NGÀNH THEO KHOA
    @GetMapping("/nganh/{maKhoa}")
    public List<Nganh> DSNganhTheoKhoa(@PathParam(value = "maKhoa") String maKhoa){
        return nganhRepository.layDsNganhTheoKhoa(maKhoa);
    }
    
    //DANH SÁCH CHỨC VỤ
    @GetMapping("/chucvu")
    public List<Chucvu> DSChucVu() {
        return chucVuRepository.findAll();
    }
}

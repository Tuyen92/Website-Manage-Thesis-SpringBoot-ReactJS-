/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.WebKhoaLuan.repository;

import com.demo.WebKhoaLuan.model.Chitiethoidong;
import com.demo.WebKhoaLuan.model.Hoidong;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public interface HoidongRepository extends JpaRepository<Hoidong, Integer>{    
    @Query("SELECT h FROM Hoidong h")
    List<Hoidong> dsHoiDong();
    
    @Query("SELECT h FROM Hoidong h WHERE h.maHd = :#{#maHd}")
    Hoidong layHD(int maHd);
    
    @Query("SELECT c FROM Chitiethoidong c WHERE c.chitiethoidongPK.giangvienMaGv = :#{#maGv}")
    List<Chitiethoidong> layHDGV(String maGv);
    
    @Query("SELECT h FROM Hoidong h WHERE h.tinhTranghd = :#{#hoatDong}")
    List<Hoidong> dsHDHoatDong(short hoatDong);
    
    @Query("SELECT c FROM Chitiethoidong c WHERE c.chitiethoidongPK.hoidongMaHd = :#{#maHd}")
    List<Chitiethoidong> kiemSLTV(int maHd);
}

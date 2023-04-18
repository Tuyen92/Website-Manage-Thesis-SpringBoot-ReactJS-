/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.WebKhoaLuan.repository;

import com.demo.WebKhoaLuan.model.Tongketkhoaluan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public interface TongketRepository extends JpaRepository<Tongketkhoaluan, Integer>{
    @Query("SELECT t FROM Tongketkhoaluan t WHERE t.maSv = :#{#maSv}")
    Tongketkhoaluan layTongKet(String maSv);
}

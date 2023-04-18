/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ADMIN
 */
@Embeddable
public class DiemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tieuchi_ma_tc")
    private String tieuchiMaTc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "khoaluan_ma_kl")
    private int khoaluanMaKl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "khoaluan_dangkykhoaluan_ma_dk")
    private int khoaluanDangkykhoaluanMaDk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "khoaluan_dangkykhoaluan_detai_ma_dt")
    private int khoaluanDangkykhoaluanDetaiMaDt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "khoaluan_dangkykhoaluan_sinhvien_ma_sv")
    private String khoaluanDangkykhoaluanSinhvienMaSv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "giangvien_ma_gv")
    private String giangvienMaGv;

    public DiemPK() {
    }

    public DiemPK(String tieuchiMaTc, int khoaluanMaKl, int khoaluanDangkykhoaluanMaDk, int khoaluanDangkykhoaluanDetaiMaDt, String khoaluanDangkykhoaluanSinhvienMaSv, String giangvienMaGv) {
        this.tieuchiMaTc = tieuchiMaTc;
        this.khoaluanMaKl = khoaluanMaKl;
        this.khoaluanDangkykhoaluanMaDk = khoaluanDangkykhoaluanMaDk;
        this.khoaluanDangkykhoaluanDetaiMaDt = khoaluanDangkykhoaluanDetaiMaDt;
        this.khoaluanDangkykhoaluanSinhvienMaSv = khoaluanDangkykhoaluanSinhvienMaSv;
        this.giangvienMaGv = giangvienMaGv;
    }

    public String getTieuchiMaTc() {
        return tieuchiMaTc;
    }

    public void setTieuchiMaTc(String tieuchiMaTc) {
        this.tieuchiMaTc = tieuchiMaTc;
    }

    public int getKhoaluanMaKl() {
        return khoaluanMaKl;
    }

    public void setKhoaluanMaKl(int khoaluanMaKl) {
        this.khoaluanMaKl = khoaluanMaKl;
    }

    public int getKhoaluanDangkykhoaluanMaDk() {
        return khoaluanDangkykhoaluanMaDk;
    }

    public void setKhoaluanDangkykhoaluanMaDk(int khoaluanDangkykhoaluanMaDk) {
        this.khoaluanDangkykhoaluanMaDk = khoaluanDangkykhoaluanMaDk;
    }

    public int getKhoaluanDangkykhoaluanDetaiMaDt() {
        return khoaluanDangkykhoaluanDetaiMaDt;
    }

    public void setKhoaluanDangkykhoaluanDetaiMaDt(int khoaluanDangkykhoaluanDetaiMaDt) {
        this.khoaluanDangkykhoaluanDetaiMaDt = khoaluanDangkykhoaluanDetaiMaDt;
    }

    public String getKhoaluanDangkykhoaluanSinhvienMaSv() {
        return khoaluanDangkykhoaluanSinhvienMaSv;
    }

    public void setKhoaluanDangkykhoaluanSinhvienMaSv(String khoaluanDangkykhoaluanSinhvienMaSv) {
        this.khoaluanDangkykhoaluanSinhvienMaSv = khoaluanDangkykhoaluanSinhvienMaSv;
    }

    public String getGiangvienMaGv() {
        return giangvienMaGv;
    }

    public void setGiangvienMaGv(String giangvienMaGv) {
        this.giangvienMaGv = giangvienMaGv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tieuchiMaTc != null ? tieuchiMaTc.hashCode() : 0);
        hash += (int) khoaluanMaKl;
        hash += (int) khoaluanDangkykhoaluanMaDk;
        hash += (int) khoaluanDangkykhoaluanDetaiMaDt;
        hash += (khoaluanDangkykhoaluanSinhvienMaSv != null ? khoaluanDangkykhoaluanSinhvienMaSv.hashCode() : 0);
        hash += (giangvienMaGv != null ? giangvienMaGv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiemPK)) {
            return false;
        }
        DiemPK other = (DiemPK) object;
        if ((this.tieuchiMaTc == null && other.tieuchiMaTc != null) || (this.tieuchiMaTc != null && !this.tieuchiMaTc.equals(other.tieuchiMaTc))) {
            return false;
        }
        if (this.khoaluanMaKl != other.khoaluanMaKl) {
            return false;
        }
        if (this.khoaluanDangkykhoaluanMaDk != other.khoaluanDangkykhoaluanMaDk) {
            return false;
        }
        if (this.khoaluanDangkykhoaluanDetaiMaDt != other.khoaluanDangkykhoaluanDetaiMaDt) {
            return false;
        }
        if ((this.khoaluanDangkykhoaluanSinhvienMaSv == null && other.khoaluanDangkykhoaluanSinhvienMaSv != null) || (this.khoaluanDangkykhoaluanSinhvienMaSv != null && !this.khoaluanDangkykhoaluanSinhvienMaSv.equals(other.khoaluanDangkykhoaluanSinhvienMaSv))) {
            return false;
        }
        if ((this.giangvienMaGv == null && other.giangvienMaGv != null) || (this.giangvienMaGv != null && !this.giangvienMaGv.equals(other.giangvienMaGv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.demo.WebKhoaLuan.model.DiemPK[ tieuchiMaTc=" + tieuchiMaTc + ", khoaluanMaKl=" + khoaluanMaKl + ", khoaluanDangkykhoaluanMaDk=" + khoaluanDangkykhoaluanMaDk + ", khoaluanDangkykhoaluanDetaiMaDt=" + khoaluanDangkykhoaluanDetaiMaDt + ", khoaluanDangkykhoaluanSinhvienMaSv=" + khoaluanDangkykhoaluanSinhvienMaSv + ", giangvienMaGv=" + giangvienMaGv + " ]";
    }
    
}

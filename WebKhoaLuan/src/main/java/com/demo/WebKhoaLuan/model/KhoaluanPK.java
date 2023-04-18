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
public class KhoaluanPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_kl")
    private int maKl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dangkykhoaluan_ma_dk")
    private int dangkykhoaluanMaDk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dangkykhoaluan_detai_ma_dt")
    private int dangkykhoaluanDetaiMaDt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "dangkykhoaluan_sinhvien_ma_sv")
    private String dangkykhoaluanSinhvienMaSv;

    public KhoaluanPK() {
    }

    public KhoaluanPK(int maKl, int dangkykhoaluanMaDk, int dangkykhoaluanDetaiMaDt, String dangkykhoaluanSinhvienMaSv) {
        this.maKl = maKl;
        this.dangkykhoaluanMaDk = dangkykhoaluanMaDk;
        this.dangkykhoaluanDetaiMaDt = dangkykhoaluanDetaiMaDt;
        this.dangkykhoaluanSinhvienMaSv = dangkykhoaluanSinhvienMaSv;
    }

    public int getMaKl() {
        return maKl;
    }

    public void setMaKl(int maKl) {
        this.maKl = maKl;
    }

    public int getDangkykhoaluanMaDk() {
        return dangkykhoaluanMaDk;
    }

    public void setDangkykhoaluanMaDk(int dangkykhoaluanMaDk) {
        this.dangkykhoaluanMaDk = dangkykhoaluanMaDk;
    }

    public int getDangkykhoaluanDetaiMaDt() {
        return dangkykhoaluanDetaiMaDt;
    }

    public void setDangkykhoaluanDetaiMaDt(int dangkykhoaluanDetaiMaDt) {
        this.dangkykhoaluanDetaiMaDt = dangkykhoaluanDetaiMaDt;
    }

    public String getDangkykhoaluanSinhvienMaSv() {
        return dangkykhoaluanSinhvienMaSv;
    }

    public void setDangkykhoaluanSinhvienMaSv(String dangkykhoaluanSinhvienMaSv) {
        this.dangkykhoaluanSinhvienMaSv = dangkykhoaluanSinhvienMaSv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maKl;
        hash += (int) dangkykhoaluanMaDk;
        hash += (int) dangkykhoaluanDetaiMaDt;
        hash += (dangkykhoaluanSinhvienMaSv != null ? dangkykhoaluanSinhvienMaSv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KhoaluanPK)) {
            return false;
        }
        KhoaluanPK other = (KhoaluanPK) object;
        if (this.maKl != other.maKl) {
            return false;
        }
        if (this.dangkykhoaluanMaDk != other.dangkykhoaluanMaDk) {
            return false;
        }
        if (this.dangkykhoaluanDetaiMaDt != other.dangkykhoaluanDetaiMaDt) {
            return false;
        }
        if ((this.dangkykhoaluanSinhvienMaSv == null && other.dangkykhoaluanSinhvienMaSv != null) || (this.dangkykhoaluanSinhvienMaSv != null && !this.dangkykhoaluanSinhvienMaSv.equals(other.dangkykhoaluanSinhvienMaSv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.demo.WebKhoaLuan.model.KhoaluanPK[ maKl=" + maKl + ", dangkykhoaluanMaDk=" + dangkykhoaluanMaDk + ", dangkykhoaluanDetaiMaDt=" + dangkykhoaluanDetaiMaDt + ", dangkykhoaluanSinhvienMaSv=" + dangkykhoaluanSinhvienMaSv + " ]";
    }
    
}

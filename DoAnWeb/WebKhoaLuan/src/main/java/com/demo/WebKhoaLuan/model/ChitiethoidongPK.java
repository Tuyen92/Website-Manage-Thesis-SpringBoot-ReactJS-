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
public class ChitiethoidongPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "hoidong_ma_hd")
    private int hoidongMaHd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "giangvien_ma_gv")
    private String giangvienMaGv;

    public ChitiethoidongPK() {
    }

    public ChitiethoidongPK(int hoidongMaHd, String giangvienMaGv) {
        this.hoidongMaHd = hoidongMaHd;
        this.giangvienMaGv = giangvienMaGv;
    }

    public int getHoidongMaHd() {
        return hoidongMaHd;
    }

    public void setHoidongMaHd(int hoidongMaHd) {
        this.hoidongMaHd = hoidongMaHd;
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
        hash += (int) hoidongMaHd;
        hash += (giangvienMaGv != null ? giangvienMaGv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChitiethoidongPK)) {
            return false;
        }
        ChitiethoidongPK other = (ChitiethoidongPK) object;
        if (this.hoidongMaHd != other.hoidongMaHd) {
            return false;
        }
        if ((this.giangvienMaGv == null && other.giangvienMaGv != null) || (this.giangvienMaGv != null && !this.giangvienMaGv.equals(other.giangvienMaGv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.demo.WebKhoaLuan.model.ChitiethoidongPK[ hoidongMaHd=" + hoidongMaHd + ", giangvienMaGv=" + giangvienMaGv + " ]";
    }
    
}

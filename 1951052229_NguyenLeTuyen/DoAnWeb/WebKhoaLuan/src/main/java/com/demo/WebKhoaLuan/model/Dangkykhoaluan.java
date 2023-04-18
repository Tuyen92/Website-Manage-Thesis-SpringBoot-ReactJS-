/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@JsonIgnoreProperties({"khoaluanSet"})
@Entity
@Table(name = "dangkykhoaluan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dangkykhoaluan.findAll", query = "SELECT d FROM Dangkykhoaluan d"),
    @NamedQuery(name = "Dangkykhoaluan.findByMaDk", query = "SELECT d FROM Dangkykhoaluan d WHERE d.dangkykhoaluanPK.maDk = :maDk"),
    @NamedQuery(name = "Dangkykhoaluan.findByDetaiMaDt", query = "SELECT d FROM Dangkykhoaluan d WHERE d.dangkykhoaluanPK.detaiMaDt = :detaiMaDt"),
    @NamedQuery(name = "Dangkykhoaluan.findBySinhvienMaSv", query = "SELECT d FROM Dangkykhoaluan d WHERE d.dangkykhoaluanPK.sinhvienMaSv = :sinhvienMaSv"),
    @NamedQuery(name = "Dangkykhoaluan.findByMaSv2", query = "SELECT d FROM Dangkykhoaluan d WHERE d.maSv2 = :maSv2"),
    @NamedQuery(name = "Dangkykhoaluan.findByNgayDk", query = "SELECT d FROM Dangkykhoaluan d WHERE d.ngayDk = :ngayDk"),
    @NamedQuery(name = "Dangkykhoaluan.findByXetDuyet", query = "SELECT d FROM Dangkykhoaluan d WHERE d.xetDuyet = :xetDuyet")})
public class Dangkykhoaluan implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DangkykhoaluanPK dangkykhoaluanPK;
    @Size(max = 10)
    @Column(name = "ma_sv2")
    private String maSv2;
    @Column(name = "ngay_dk")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayDk;
    @Column(name = "xet_duyet")
    private Integer xetDuyet;
    @JoinColumn(name = "detai_ma_dt", referencedColumnName = "ma_dt", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Detai detai;
    @JoinColumn(name = "sinhvien_ma_sv", referencedColumnName = "ma_sv", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sinhvien sinhvien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dangkykhoaluan")
    private Set<Khoaluan> khoaluanSet;

    public Dangkykhoaluan() {
    }

    public Dangkykhoaluan(DangkykhoaluanPK dangkykhoaluanPK) {
        this.dangkykhoaluanPK = dangkykhoaluanPK;
    }

    public Dangkykhoaluan(int maDk, int detaiMaDt, String sinhvienMaSv) {
        this.dangkykhoaluanPK = new DangkykhoaluanPK(maDk, detaiMaDt, sinhvienMaSv);
    }

    public DangkykhoaluanPK getDangkykhoaluanPK() {
        return dangkykhoaluanPK;
    }

    public void setDangkykhoaluanPK(DangkykhoaluanPK dangkykhoaluanPK) {
        this.dangkykhoaluanPK = dangkykhoaluanPK;
    }

    public String getMaSv2() {
        return maSv2;
    }

    public void setMaSv2(String maSv2) {
        this.maSv2 = maSv2;
    }

    public Date getNgayDk() {
        return ngayDk;
    }

    public void setNgayDk(Date ngayDk) {
        this.ngayDk = ngayDk;
    }

    public Integer getXetDuyet() {
        return xetDuyet;
    }

    public void setXetDuyet(Integer xetDuyet) {
        this.xetDuyet = xetDuyet;
    }

    public Detai getDetai() {
        return detai;
    }

    public void setDetai(Detai detai) {
        this.detai = detai;
    }

    public Sinhvien getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(Sinhvien sinhvien) {
        this.sinhvien = sinhvien;
    }

    @XmlTransient
    public Set<Khoaluan> getKhoaluanSet() {
        return khoaluanSet;
    }

    public void setKhoaluanSet(Set<Khoaluan> khoaluanSet) {
        this.khoaluanSet = khoaluanSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dangkykhoaluanPK != null ? dangkykhoaluanPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dangkykhoaluan)) {
            return false;
        }
        Dangkykhoaluan other = (Dangkykhoaluan) object;
        if ((this.dangkykhoaluanPK == null && other.dangkykhoaluanPK != null) || (this.dangkykhoaluanPK != null && !this.dangkykhoaluanPK.equals(other.dangkykhoaluanPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.demo.WebKhoaLuan.model.Dangkykhoaluan[ dangkykhoaluanPK=" + dangkykhoaluanPK + " ]";
    }
    
}

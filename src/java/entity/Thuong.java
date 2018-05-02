/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author hieuhayho
 */
@Entity
@Table(name="thuong")
public class Thuong {
    @Id
    private int id;
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date ngaydanhgia;
        private Double mark;
        private int idstudent;

    public int getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

    public Thuong(int id, Date ngaydanhgia, Double mark, int idstudent) {
        this.id = id;
        this.ngaydanhgia = ngaydanhgia;
        this.mark = mark;
        this.idstudent = idstudent;
    }

    public Thuong() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgaydanhgia() {
        return ngaydanhgia;
    }

    public void setNgaydanhgia(Date ngaydanhgia) {
        this.ngaydanhgia = ngaydanhgia;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Thuong(int id, Date ngaydanhgia, Double mark) {
        this.id = id;
        this.ngaydanhgia = ngaydanhgia;
        this.mark = mark;
    }
    
}

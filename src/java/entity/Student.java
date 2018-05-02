package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Students")
public class Student {
	@Id 
	@GeneratedValue
	private Integer id;
	private String fullname;
	private Boolean gender;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date birthday;
	private Double mark;
        	@ManyToOne
	@JoinColumn(name="majorId")
	private Major major;
        private String photo;
        private String email;
        private Double thanhtich;
        private Double kyluat;
        @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date ngaydanhgia;

    public Student(Integer id, String fullname, Boolean gender, Date birthday, Double mark, Major major, String photo, String email, Double thanhtich, Double kyluat, Date ngaydanhgia) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.birthday = birthday;
        this.mark = mark;
        this.major = major;
        this.photo = photo;
        this.email = email;
        this.thanhtich = thanhtich;
        this.kyluat = kyluat;
        this.ngaydanhgia = ngaydanhgia;
    }

    public Date getNgaydanhgia() {
        return ngaydanhgia;
    }

    public void setNgaydanhgia(Date ngaydanhgia) {
        this.ngaydanhgia = ngaydanhgia;
    }

    public Student(Integer id, String fullname, Boolean gender, Date birthday, Double mark, Major major, String photo, String email, Double thanhtich, Double kyluat) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.birthday = birthday;
        this.mark = mark;
        this.major = major;
        this.photo = photo;
        this.email = email;
        this.thanhtich = thanhtich;
        this.kyluat = kyluat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getThanhtich() {
        return thanhtich;
    }

    public void setThanhtich(Double thanhtich) {
        this.thanhtich = thanhtich;
    }

    public Double getKyluat() {
        return kyluat;
    }

    public void setKyluat(Double kyluat) {
        this.kyluat = kyluat;
    }
        	


    public Student() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

 
    
    

    


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
}

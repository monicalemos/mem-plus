package pt.memplus.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="Doctors")
public class Doctor extends Person{
//	@Column(name = "speciality", nullable = false)
	private String speciality;
	
	public Doctor() {
		super();
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
}

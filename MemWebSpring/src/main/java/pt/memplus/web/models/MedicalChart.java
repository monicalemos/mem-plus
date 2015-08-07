package pt.memplus.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//TODO
//@Entity
//@Table(name="MedicalCharts")
public class MedicalChart {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String education;
	private String sicknessLevel;
	private Doctor doctor;
	private Iterable<Technician> technician;
	private Iterable<CareGiver> caregiver;
	private Iterable<TherapySession> session;
	
	public MedicalChart() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSicknessLevel() {
		return sicknessLevel;
	}

	public void setSicknessLevel(String sicknessLevel) {
		this.sicknessLevel = sicknessLevel;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Iterable<Technician> getTechnician() {
		return technician;
	}

	public void setTechnician(Iterable<Technician> technician) {
		this.technician = technician;
	}

	public Iterable<CareGiver> getCaregiver() {
		return caregiver;
	}

	public void setCaregiver(Iterable<CareGiver> caregiver) {
		this.caregiver = caregiver;
	}

	public Iterable<TherapySession> getSession() {
		return session;
	}

	public void setSession(Iterable<TherapySession> session) {
		this.session = session;
	}
	
	
	
	
}

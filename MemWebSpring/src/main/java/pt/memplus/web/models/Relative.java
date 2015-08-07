package pt.memplus.web.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
//TODO
//@Entity
//@Table(name="Relatives")
public class Relative extends Person{
	private String relation;
	private int patientId;
	private Date dateOfDeath;
	private boolean isCareGiver;
	private Address address;
	
	public Relative() {
		super();
		address = new Address();
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public boolean isCareGiver() {
		return isCareGiver;
	}

	public void setCareGiver(boolean isCareGiver) {
		this.isCareGiver = isCareGiver;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
}

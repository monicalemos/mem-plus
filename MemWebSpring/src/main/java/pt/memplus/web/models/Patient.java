package pt.memplus.web.models;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Table;

//TODO
//@Entity
//@Table(name="Patients")
public class Patient extends Person{
	private Address address;
	private MedicalChart medicalChart;
	private Iterable<Relative> relatives;
	
	
	public Patient() {
		super();
		address = new Address();
		medicalChart = new MedicalChart();
		relatives = new LinkedList<Relative>();
	}

	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public MedicalChart getMedicalChart() {
		return medicalChart;
	}


	public void setMedicalChart(MedicalChart medicalChart) {
		this.medicalChart = medicalChart;
	}


	public Iterable<Relative> getRelatives() {
		return relatives;
	}


	public void setRelatives(Iterable<Relative> relatives) {
		this.relatives = relatives;
	}

	
}

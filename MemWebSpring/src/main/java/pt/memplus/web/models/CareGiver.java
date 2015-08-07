package pt.memplus.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
//
//@Entity
//@Table(name="CareGivers")
public class CareGiver extends Person {
//	@Column(name = "caregiver", nullable = false)
	private boolean caregiver;
	
	public CareGiver() {
		super();
	}

	public boolean isCaregiver() {
		return caregiver;
	}

	public void setCaregiver(boolean caregiver) {
		this.caregiver = caregiver;
	}

}

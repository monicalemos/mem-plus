package pt.memplus.web.models;

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

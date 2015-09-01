package pt.memplus.web.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//@Table(name="TherapySessions")
public class TherapySession {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public TherapySession() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}

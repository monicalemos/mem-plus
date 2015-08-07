package pt.memplus.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

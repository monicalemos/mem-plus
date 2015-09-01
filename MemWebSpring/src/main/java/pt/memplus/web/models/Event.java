package pt.memplus.web.models;

import java.util.Date;
//TODO
//@Entity
//@Table(name="Events")
public class Event {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int relatedToPersonId;
	private String title;
	private String description;
	private Date  dateOfOccurance;
	
	public Event() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRelatedToPersonId() {
		return relatedToPersonId;
	}
	public void setRelatedToPersonId(int personId) {
		this.relatedToPersonId = personId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateOfOccurance() {
		return dateOfOccurance;
	}
	public void setDateOfOccurance(Date dateOfOccurance) {
		this.dateOfOccurance = dateOfOccurance;
	}
	
	
	

}

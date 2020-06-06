package tn.esprit.spring.Model;

public class Statliv {
	public Integer nbmission;
	public String username;
	public Integer getNbmission() {
		return nbmission;
	}
	public void setNbmission(Integer nbmission) {
		this.nbmission = nbmission;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Statliv(Integer nbmission, String username) {
		super();
		this.nbmission = nbmission;
		this.username = username;
	}
	
	

}

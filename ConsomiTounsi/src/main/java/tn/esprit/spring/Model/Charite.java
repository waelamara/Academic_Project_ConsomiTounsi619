package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CHARITE")
public class Charite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 
	 private String TypeCharite;
	 @ManyToOne
	private User iduser;
	 @ManyToOne
private Events idevents ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeCharite() {
		return TypeCharite;
	}
	public void setTypeCharite(String typeCharite) {
		TypeCharite = typeCharite;
	}
	public User getIduser() {
		return iduser;
	}
	public void setIduser(User iduser) {
		this.iduser = iduser;
	}
	public Events getIdevents() {
		return idevents;
	}
	public void setIdevents(Events idevents) {
		this.idevents = idevents;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 

}

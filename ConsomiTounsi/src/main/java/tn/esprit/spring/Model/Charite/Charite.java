package tn.esprit.spring.Model.Charite;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Commande;


@Entity
@Table(name = "T_CHARITE")
public class Charite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 
	private String typeCharite;
	private float montantPaye;
	@ManyToOne
	@JsonIgnore
	private User iduser;
	@ManyToOne
	@JsonIgnore
    private Events idevents ;
	@ManyToMany(cascade = CascadeType.ALL) 
	@JsonIgnore
	private Set<Commande> CommandeCharite = new HashSet<>() ; 
	@Transient
	long commande_id;
	@Transient
	long event_id;
	
	
	
	
	public long getCommande_id() {
		return commande_id;
	}
	public void setCommande_id(long commande_id) {
		this.commande_id = commande_id;
	}
	public long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}
	public Set<Commande> getCommandeCharite() {
		return CommandeCharite;
	}
	public void setCommandeCharite(Set<Commande> commandeCharite) {
		CommandeCharite = commandeCharite;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	public String getTypeCharite() {
		return typeCharite;
	}
	public void setTypeCharite(String typeCharite) {
		this.typeCharite = typeCharite;
	}
	public float getMontantPaye() {
		return montantPaye;
	}
	public void setMontantPaye(float montantPaye) {
		this.montantPaye = montantPaye;
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
	public Charite() {
		super();

	}
	public Charite(String TypeCharite,float MontantPaye,User iduser,Events idevents,Set<Commande> CommandeCharite) {
		super();
		this.typeCharite=TypeCharite;
		this.montantPaye=MontantPaye;
		this.idevents=idevents;
		this.iduser=iduser;
		this.CommandeCharite=CommandeCharite;

	}
	public Charite(String TypeCharite,float MontantPaye,User iduser,Events idevents) {
		super();
		this.typeCharite=TypeCharite;
		this.montantPaye=MontantPaye;
		this.idevents=idevents;
		this.iduser=iduser;

	}
	public Charite(Set<Commande> CommandeCharite) {
		super();
		
		this.CommandeCharite=CommandeCharite;

	}

}

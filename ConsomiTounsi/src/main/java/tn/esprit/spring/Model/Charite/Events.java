package tn.esprit.spring.Model.Charite;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "T_EVENTS")
@JsonIgnoreProperties
public class Events implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date DateE;
	private int nbplace;
	private int nbparticipant;
	private String description;
	private String image;
	@OneToOne
	@JsonIgnore
	private Pub publicite;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventss")
	private Set<Endroit> endroit;
	@OneToMany(mappedBy = "idevents")
	public Set<Charite> charite;

	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateE() {
		return DateE;
	}

	public void setDateE(Date dateE) {
		DateE = dateE;
	}

	public int getNbplace() {
		return nbplace;
	}

	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}

	public int getNbparticipant() {
		return nbparticipant;
	}

	public void setNbparticipant(int nbparticipant) {
		this.nbparticipant = nbparticipant;
	}

	public Pub getPublicite() {
		return publicite;
	}

	public void setPublicite(Pub publicite) {
		this.publicite = publicite;
	}

	public Set<Endroit> getEndroit() {
		return endroit;
	}

	public void setEndroit(Set<Endroit> endroit) {
		this.endroit = endroit;
	}

	public Set<Charite> getCharite() {
		return charite;
	}

	public void setCharite(Set<Charite> charite) {
		this.charite = charite;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Events(String titre, Date DateE, int nbplace, int nbparticipant, Pub publicite, Set<Endroit> endroit,
			Set<Charite> charite,String description,String image) {
		super();
		this.titre = titre;
		this.DateE = DateE;
		this.nbplace = nbplace;
		this.nbparticipant = nbparticipant;
		this.publicite = publicite;
		this.endroit = endroit;
		this.charite = charite;
		this.description = description;
		this.image=image;

	}
	public Events(long id,String titre, Date DateE, int nbplace, int nbparticipant, Pub publicite, Set<Endroit> endroit,
			Set<Charite> charite,String description,String image) {
		super();
		this.Id=id;
		this.titre = titre;
		this.DateE = DateE;
		this.nbplace = nbplace;
		this.nbparticipant = nbparticipant;
		this.publicite = publicite;
		this.endroit = endroit;
		this.charite = charite;
		this.description = description;
		this.image=image;

	}

	public Events() {
		super();

	}
	public Events(long idevents) {
		super();
		this.Id=idevents;

	}

}

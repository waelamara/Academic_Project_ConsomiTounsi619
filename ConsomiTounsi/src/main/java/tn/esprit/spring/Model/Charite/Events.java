package tn.esprit.spring.Model.Charite;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Long id;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date dateE;
	private int nbplace;
	private int nbparticipant;
	private String description;
	private String image;
	@OneToOne
	@JsonIgnore
	private Pub publicite;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "eventss")
	private Set<Endroit> endroit;
	@OneToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "idevents")
	public Set<Charite> charite;

	

	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	

	public Date getDateE() {
		return dateE;
	}

	public void setDateE(Date dateE) {
		this.dateE = dateE;
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
		this.dateE = DateE;
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
		this.id=id;
		this.titre = titre;
		this.dateE = DateE;
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
		this.id=idevents;

	}
	public Events(String titre,String description, Date dateE, int nbplace, int nbparticipant,String image) {
		super();
		this.titre = titre;
		this.dateE = dateE;
		this.nbplace = nbplace;
		this.nbparticipant = nbparticipant;
		this.description = description;
		this.image=image;

	}
	public Events(String titre,String description, Date dateE, int nbplace, int nbparticipant) {
		super();
		this.titre = titre;
		this.dateE = dateE;
		this.nbplace = nbplace;
		this.nbparticipant = nbparticipant;
		this.description = description;
		

	}

}

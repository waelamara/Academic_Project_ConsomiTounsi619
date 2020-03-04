package tn.esprit.spring.Model;
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

@Entity
@Table(name = "T_EVENTS")
public class Events implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String titre;
	@Temporal (TemporalType.DATE)
	private Date DateE;
	private int nbplace;
	private int nbparticipant;
	@OneToOne 
	private Pub publicite; 
	@OneToMany(cascade = CascadeType.ALL, mappedBy="eventss") 
	private Set<Endroit> endroit;
	@OneToMany(mappedBy="idevents")
	public Set<Charite> charite;


}

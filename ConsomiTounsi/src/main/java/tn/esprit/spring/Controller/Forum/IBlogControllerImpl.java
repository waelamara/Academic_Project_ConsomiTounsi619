package tn.esprit.spring.Controller.Forum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Service.Forum.ISujetService;

@Controller(value = "blogController")
@ELBeanName(value = "blogController")
@Join(path = "/blog", to = "/blog.jsf")
public class IBlogControllerImpl{
	@Autowired
	ISujetService iSujetService;

	private Long id;
	private String nomSujet;
	private String description;
	@Temporal (TemporalType.DATE)
    private Date dateAjout;
	private int nbVue;
	private int nbLike;
	private int nbDislike;
	private int nbpoint;
	private List<Sujet> sujets;
	private Sujet sujet;
	User idUser;
	
	public User getIdUser() {
		return idUser;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomSujet() {
		return nomSujet;
	}
	public void setNomSujet(String nomSujet) {
		this.nomSujet = nomSujet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public int getNbVue() {
		return nbVue;
	}
	public void setNbVue(int nbVue) {
		this.nbVue = nbVue;
	}
	public int getNbLike() {
		return nbLike;
	}
	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}
	public int getNbDislike() {
		return nbDislike;
	}
	public void setNbDislike(int nbDislike) {
		this.nbDislike = nbDislike;
	}
	public int getNbpoint() {
		return nbpoint;
	}
	public void setNbpoint(int nbpoint) {
		this.nbpoint = nbpoint;
	}
	
	/********show all sujets****/
	public List<Sujet> getAllSujets() {
		return iSujetService.getAllSujets();
	}
	
	/******shoow one sujet******/
	public Sujet getSujet() {
		Sujet v =iSujetService.getAllSujets().get(0);
		setNomSujet(v.getNomSujet());
		return v;
	}
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	

	public String convertireDate(Date D){
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
		return formatter.format(D);
	}
	

}

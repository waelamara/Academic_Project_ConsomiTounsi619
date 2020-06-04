package tn.esprit.spring.Controller.Charite;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Repository.Charite.EndroitRepository;
import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.Service.Charite.EndroitDAO;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Controller(value = "ControllerEventsUpdate")
@ELBeanName(value = "ControllerEventsUpdate")
@Join(path = "/UpdateEvent", to = "/UpdateEvent.jsf")
public class ControllerEventsUpdate {
	@Autowired
	EventsDAO eventDAO;
	@Autowired
	CommandeImpl commandeDao;
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	EndroitDAO endroitDAO;
	@Autowired
	UserService userDAO;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	public static final String ACCOUNT_SID = "AC25eeab7c940f79dd272d5bc2d7337437";
	  public static final String AUTH_TOKEN = "cf00808dd9240106de0943465ae7408e";
	
	private Long Id;
	private String titre;
	private Date dateE;
	private int nbplace;
	private int nbparticipant;
	private String description;
	private String image;
	private UploadedFiles files;
	@Autowired
	private EndroitRepository endroitRepository;
	 public static Long ide ;
	
	
	
	
	public UploadedFiles getFiles() {
		return files;
	}

	public void setFiles(UploadedFiles files) {
		this.files = files;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	 public List<Endroit> getAllEndroitE(Long id) {
			
			return endroitDAO.getAllEndroitEv(id);
		}
	
	 public String updateEvent(Events e,Long idevents) {
			
		 ide = idevents;
			System.out.println(idevents);
			this.description=e.getDescription();
			this.dateE=e.getDateE();
			this.nbparticipant=e.getNbparticipant();
			this.titre=e.getTitre();
			this.nbplace=e.getNbplace();
			this.image=e.getImage();
		/* return eventDAO.updateEvent(e, e.getId(), e.getTitre(),
				e.getDateE(), e.getNbplace(), e.getNbparticipant(), 
				e.getDescription(), e.getImage());*/
		return "/UpdateEvent.xhtml?faces-redirect=true";
		
	}
	/* public String DisplayProduits(Produit p){
			
			this.idToUpdate=p.getId();
			this.nomProduit=p.getNomProduit();
			this.description=p.getDescription();
			this.prix=p.getPrix();
			this.poids=p.getPoids();
			this.prixAchat=p.getPrixAchat();
			this.barcode=p.getBarcode();
			
			return "/pages/AdminFormProduit.xhtml?faces-redirect=true";
		}*/
	 @Transactional
	 public String updateE()
		{
			Events e = new Events();
			e=eventDAO.findOne(ide);
			e.setTitre(titre);
			e.setDateE(dateE);
			e.setDescription(description);
			e.setNbplace(nbplace);
			e.setNbparticipant(nbparticipant);
			e.setEndroit(e.getEndroit());
			e.setCharite(e.getCharite());
			e.setImage(e.getImage());
			eventDAO.saveEventss(e, files);
			FacesMessage facesMessage =

					new FacesMessage("Events Updated with Sucess");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
			return "EventAdmin.xhtml?faces-redirect=true";
			
		}

}

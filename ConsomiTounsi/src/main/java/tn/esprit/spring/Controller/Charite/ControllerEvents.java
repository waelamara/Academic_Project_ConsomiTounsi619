package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import Utils.AppConstants;
import tn.esprit.spring.Controller.Charite.RepeatPaginator2;
import tn.esprit.spring.Controller.Forum.RepeatPaginator;
import tn.esprit.spring.Controller.GestionUser.LoginController;
import tn.esprit.spring.Controller.GestionUser.RepeatPaginator1;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.ImageUser;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Repository.Charite.EndroitRepository;
import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.Service.Charite.EndroitDAO;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;
import tn.esprit.spring.security.services.UserDetailsImpl;

@Controller(value = "ControllerEvents")
@ELBeanName(value = "ControllerEvents")
@Join(path = "/AddEvent", to = "/AddEvent.jsf")
@ViewScoped
public class ControllerEvents {
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
	 private RepeatPaginator2 paginatorRec;
	 private RepeatPaginator2 paginatorRec1;
	
	
	
	
	public RepeatPaginator2 getPaginatorRec() {
		return paginatorRec;
	}

	public void setPaginatorRec(RepeatPaginator2 paginatorRec) {
		this.paginatorRec = paginatorRec;
	}

	public RepeatPaginator2 getPaginatorRec1() {
		return paginatorRec1;
	}

	public void setPaginatorRec1(RepeatPaginator2 paginatorRec1) {
		this.paginatorRec1 = paginatorRec1;
	}

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

	public String addEvents(Events e) {
		eventDAO.saveEvents(e);
		return "Successful";
	}

	public List<Events> getAllEvents() {
		return eventDAO.getAllEventsList();
	}

	public String modifEvents(Events e) {
		eventDAO.upsateEvents(e);
		return "Successful";

	}
	
	public String delete(long Id) {

		eventDAO.deleteEventsById(Id);
		return "/EventAdmin.xhtml?faces-redirect=true";

	}
	public List<Events> findLikeNameM(String titre) {
		return eventDAO.findLikeName(titre);
	}
	/*****evenement du jour*********/
	public List<Events> getEventsParDate() {
		return eventDAO.getEventsParDate();
	}
	
	
	
	
	
	public List<Endroit> getAllEndroit() {
		
		return endroitDAO.getAllEndroitList();
	}
	public int addPub(Long publicite,Events Events) {

		return eventDAO.saveEvent(publicite, Events);
	}
	public Endroit addEndroit(Endroit Endroit) {

		return endroitDAO.saveEndroit1(Endroit);
	}
	public String addEv() {
	
		eventDAO.saveEventss(new Events(titre, description, dateE, nbplace, nbparticipant), files);
		return "/EventAdmin.xhtml?faces-redirect=true";
	}
	 public String save() {
		 
	       return eventDAO.save();
	    }
	
	 public List<Endroit> getAllEndroitE(Long id) {
			
			return endroitDAO.getAllEndroitEv(id);
		}
	
	 
	
	
	 
		
		@PostConstruct
		//@Scheduled(cron="*/10 * * * * *")
		public void init1(){
			paginatorRec1=new RepeatPaginator2(getEventsParDate());
		}
		@PostConstruct
		//@Scheduled(cron="*/10 * * * * *")
		public void init(){
			List<Events> c= getAllEvents();
		paginatorRec = new RepeatPaginator2(c);
	}
	
}


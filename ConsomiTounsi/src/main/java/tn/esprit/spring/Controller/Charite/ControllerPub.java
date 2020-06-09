package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.Charite.PubDAO;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Controller(value = "ControllerPub")
@ELBeanName(value = "ControllerPub")
@Join(path = "/AddPub", to = "/AddPub.jsf")
@ViewScoped
public class ControllerPub {
	@Autowired
	PubDAO publiciteDAO;
	@Autowired
	EventsDAO eventDAO;
	private Long Id;
	private String nom;
	private Date dateDebut;
	
	private Date dateFin;
	private String image;
	private UploadedFiles files;
	 public static Long ide ;
	 private String nomRecherchePub;
	 
	 private RepeatPaginator2 paginatorRec;
	 
	 
	
	public String getNomRecherchePub() {
		return nomRecherchePub;
	}
	public void setNomRecherchePub(String nomRecherchePub) {
		this.nomRecherchePub = nomRecherchePub;
	}
	public RepeatPaginator2 getPaginatorRec() {
			return paginatorRec;
		}
		public void setPaginatorRec(RepeatPaginator2 paginatorRec) {
			this.paginatorRec = paginatorRec;
		}
		@PostConstruct
		//@Scheduled(cron="*/10 * * * * *")
		public void init(){
			paginatorRec=new RepeatPaginator2(AfficherPub());
		}
	
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UploadedFiles getFiles() {
		return files;
	}

	public void setFiles(UploadedFiles files) {
		this.files = files;
	}

	 public String addPubEvent(Long idevents) {
			
		 ide = idevents;
			System.out.println(idevents);
		return "/AddPub.xhtml?faces-redirect=true";
		
	}
	public List<Pub> AfficherPub() {
		return publiciteDAO.findAll();
	}

	public String DeletePub(Long idPub) {
		Pub p = publiciteDAO.findOne(idPub);
		p.setEvents(null);

		publiciteDAO.Delete(p);
		return "/EventPub.xhtml?faces-redirect=true";
	}

	public String EditPublicite(Pub p) {

		p.setNom(p.getNom());
		p.setDateDebut(p.getDateDebut());
		p.setDateFin(p.getDateFin());
		p.setEvents(p.getEvents());

		publiciteDAO.save(p);
		publiciteDAO.save(p);
		return "Successful";

	}
	@Transactional
	public String addPub() {
	//	this.dateDebut=publiciteDAO.ConvertirDate(dateDebutString);
     //   this.dateFin=publiciteDAO.ConvertirDate(dateFinString);
		Events e = eventDAO.findOne(ide);
		
		publiciteDAO.savePub(ide, new Pub(nom, dateDebut, dateFin, e), files);
		return "/EventPub.xhtml?faces-redirect=true";
	}
	public List<Pub> getAllPub(){
		
			return publiciteDAO.findLikeName(nomRecherchePub);
		
	}
	public String DisplayPublicite(Pub p){
		this.nom=p.getNom();
		this.dateDebut=p.getDateDebut();
		this.dateFin=p.getDateFin();
		
		this.image=p.getImage();
		return "AddPub.xhtml?faces-redirect=true";
	}
	public List<Pub> findLikeNameM(String name) {
		return publiciteDAO.findLikeName(nom);
	}

}

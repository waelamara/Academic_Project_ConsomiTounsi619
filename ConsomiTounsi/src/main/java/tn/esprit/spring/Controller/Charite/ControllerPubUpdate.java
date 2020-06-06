package tn.esprit.spring.Controller.Charite;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.Charite.PubDAO;

@Controller(value = "ControllerPubUpdate")
@ELBeanName(value = "ControllerPubUpdate")
@Join(path = "/updatePub", to = "/UpdatePub.jsf")
public class ControllerPubUpdate {
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
	 public static Long idp ;
	
	
	
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

	public String updatePub(Pub p,Long idpub) {
		
		 idp = idpub;
			System.out.println(idp);
			this.nom=p.getNom();
			this.dateDebut=p.getDateDebut();
			this.dateFin=p.getDateFin();
			
			this.image=p.getImage();
		
		return "/UpdatePub.xhtml?faces-redirect=true";
		
	}
	 @Transactional
	 public String updateP()
		{
			Pub e = new Pub();
			e=publiciteDAO.findOne(idp);
			e.setNom(nom);
			e.setDateDebut(dateDebut);
			e.setDateFin(dateFin);
			e.setImage(e.getImage());
			publiciteDAO.updatePub(e, files);
			FacesMessage facesMessage =

					new FacesMessage("Pub Updated with Sucess");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
					return "/EventPub.xhtml?faces-redirect=true";
			
		}
}

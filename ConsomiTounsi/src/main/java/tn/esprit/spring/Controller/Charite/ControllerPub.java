package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.Charite.PubDAO;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Controller(value = "ControllerPub")
@ELBeanName(value = "ControllerPub")
public class ControllerPub {
	@Autowired
	PubDAO publiciteDAO;
	@Autowired
	EventsDAO eventDAO;
	private Long Id;
	private String Nom;
	@Temporal (TemporalType.DATE)
	private Date DateDebut;
	@Temporal (TemporalType.DATE)
	private Date DateFin;
	private String Image;
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public Date getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		DateDebut = dateDebut;
	}

	public Date getDateFin() {
		return DateFin;
	}

	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String AjouterPub(Long idevents, Pub p)
			throws JsonParseException, JsonMappingException, IOException, ParseException {

		Events e1 = eventDAO.findOne(idevents);

		publiciteDAO.save(p);
		e1.setPublicite(p);
		eventDAO.saveEvents(e1);

		String dateDebut = p.getDateDebut().toString();
		String dateFin = p.getDateFin().toString();
		return "Successful" + " " + publiciteDAO.DifferenceJourDateDebutEtDateFin(dateDebut, dateFin) + " " + "days";
	}

	public List<Pub> AfficherPub() {
		return publiciteDAO.findAll();
	}

	public String DeletePub(Long idPub) {
		Pub p = publiciteDAO.findOne(idPub);

		publiciteDAO.Delete(p);
		return "Successful";
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

}

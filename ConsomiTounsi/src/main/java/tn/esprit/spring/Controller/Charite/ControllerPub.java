package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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

@Controller
public class ControllerPub {
	@Autowired
	PubDAO publiciteDAO;
	@Autowired
	EventsDAO eventDAO;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	ObjectMapper objectMapper = new ObjectMapper();
	
	public String AjouterPub( Long idevents,
			String PubJson,
			List<MultipartFile> file) 
					throws JsonParseException, JsonMappingException,IOException, ParseException{
		
		Pub p = objectMapper.readValue(PubJson, Pub.class);
		Events e1 = eventDAO.findOne(idevents);
		

		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			p.setImage(fileDownloadUri);			
			publiciteDAO.save(p);
			e1.setPublicite(p);
			eventDAO.saveEvents(e1);
			

		}
		String dateDebut = p.getDateDebut().toString();
		String dateFin = p.getDateFin().toString();
		return"Successful"+" "+publiciteDAO.DifferenceJourDateDebutEtDateFin(dateDebut, dateFin)+" "+"days";
	}
	
	public List<Pub> AfficherPub() {
		return publiciteDAO.findAll();
	}
	
	public String DeletePub(Long idPub) {
		Pub p = publiciteDAO.findOne(idPub);
		
		publiciteDAO.Delete(p);
		return "Successful";
	}
	public String EditPublicite( String ps, List<MultipartFile> file) 
			throws JsonParseException, JsonMappingException,IOException {
		
		Pub p = objectMapper.readValue(ps, Pub.class);
		p.setNom(p.getNom());
		p.setDateDebut(p.getDateDebut());
		p.setDateFin(p.getDateFin());
		p.setEvents(p.getEvents());
		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			p.setImage(fileDownloadUri);
			publiciteDAO.save(p);

		}
		 publiciteDAO.save(p);
		return "Successful";

	}

}

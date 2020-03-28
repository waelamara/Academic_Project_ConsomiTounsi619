package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.DAO.FileStorageServiceImpl;
import tn.esprit.spring.DAO.Charite.EventsDAO;
import tn.esprit.spring.DAO.Charite.PubDAO;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Pub;

@RestController
@RequestMapping("/pubEvent")
public class ControllerPub {
	@Autowired
	PubDAO publiciteDAO;
	@Autowired
	EventsDAO eventDAO;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@PostMapping("/ajouter/{idevents}")
	public String AjouterPub(@PathVariable(value = "idevents") Long idevents,
			@RequestParam(value = "Pub", required = true) String PubJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file) 
					throws JsonParseException, JsonMappingException,IOException{
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
		return"Successful"; 
	}
	
	@GetMapping("/afficher")
	public List<Pub> AfficherPub() {
		return publiciteDAO.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Pub> DeletePub(@PathVariable(value = "id") Long idPub) {
		Pub p = publiciteDAO.findOne(idPub);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		publiciteDAO.Delete(p);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/edit/{id}")
	public ResponseEntity<Pub> EditProduit(@PathVariable(value = "id") Long idPub,@Valid @RequestBody Pub p) {
		Pub p2 = publiciteDAO.findOne(idPub);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		p2.setNom(p.getNom());
		p2.setDateDebut(p.getDateDebut());
		p2.setDateFin(p.getDateFin());
		p2.setImage(p.getImage());
		p2.setEvents(p.getEvents());
		Pub PubliciteModifier = publiciteDAO.save(p2);
		return ResponseEntity.ok().body(PubliciteModifier);

	}

}

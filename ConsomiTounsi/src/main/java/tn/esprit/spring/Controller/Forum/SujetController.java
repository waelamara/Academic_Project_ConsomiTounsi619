package tn.esprit.spring.Controller.Forum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.DAO.FileStorageServiceImpl;
import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Forum.ImageSujet;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Service.Forum.IImageSujetService;
import tn.esprit.spring.Service.Forum.ISujetService;


@RestController
@RequestMapping("/sujet")
public class SujetController {
  @Autowired
  ISujetService isujetservice;
  @Autowired
  IImageSujetService iImageSujetService;
  @Autowired
  FileStorageServiceImpl fileStorageServiceImpl;
  
  ObjectMapper objectMapper = new ObjectMapper();
  
  @PostMapping("/ajouter/{categId}/{userId}")
  public ResponseEntity<?> ajouterSujet( @PathVariable(value = "categId") Long categid,
		                              @PathVariable(value = "userId") Long userid,
		                              @RequestParam(value = "Sujet", required = true) String SujetJson,
		                  			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file)
		                throws JsonParseException, JsonMappingException, IOException {
	  Sujet s= objectMapper.readValue(SujetJson, Sujet.class);
    isujetservice.ajouterSujet(s, categid, userid);
    
    for (MultipartFile i : file) {
		String fileName = fileStorageServiceImpl.storeFile(i);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();

		ImageSujet image = new ImageSujet();
		image.setImage(fileDownloadUri);
		image.setSujetId(s);
		iImageSujetService.ajouterImage(image);
	}
	  return ResponseEntity.created(null).body(s);
	  
  }
	@GetMapping("/afficher")
	public List<Sujet> AfficherSujets() {
		return isujetservice.getAllSujets();
	}
	

	@GetMapping("/afficher/{sujetId}")
	public Sujet AffichersujetbyId(@PathVariable(value = "sujetId") Long sujetId) {
		return isujetservice.findOne(sujetId);
	}
	
	@DeleteMapping("/delete/{id}/{userId}")
	public ResponseEntity<CategorieSujet> DeleteSujet(@PathVariable(value = "id") Long id,
														@PathVariable(value = "userId") Long userid) {
		Sujet s = isujetservice.findOne(id);
		if (s== null) {
			return ResponseEntity.notFound().build();
		}
		if(isujetservice.deleteSujetById(id, userid)==0)
		return ResponseEntity.notFound().build();
	return ResponseEntity.ok().build();
	}
	@GetMapping("/recherche/{nom}")
	public ResponseEntity<?> findLikeName(@PathVariable(value = "nom") String name) {
	      List<Sujet> sujets = new ArrayList<>();
		sujets=isujetservice.findSujetbyName(name);
		if(sujets.isEmpty())
			return ResponseEntity.notFound().build();
	return 	ResponseEntity.ok().body(sujets);
	}
	
	@GetMapping("/NameUser/{sujetId}")
	public ResponseEntity<?> findNamebySujet(@PathVariable(value = "sujetId") Long sujetId) {
	return 	ResponseEntity.ok().body(isujetservice.findNamebySujet(sujetId));
	}
	
	
	@GetMapping("/recherchecatg/{catgId}")
	public  ResponseEntity <?>findNamebyCateg(@PathVariable(value = "catgId") Long catgId) {
		 List<String> noms = new ArrayList<>();
		noms =isujetservice.getAllSujetNamesByCategorie(catgId);
		if(noms.isEmpty())
			return ResponseEntity.notFound().build();
	return 	ResponseEntity.ok().body(noms);
	}

	@GetMapping("/rechercheuser/{userId}")
	public ResponseEntity<?> findNamebyuser(@PathVariable(value = "userId") Long userId) {
		 List<Sujet> sujets = new ArrayList<>();
		sujets=isujetservice.findSujetbyUser(userId);
		if(sujets.isEmpty())
			return ResponseEntity.notFound().build();
	return 	ResponseEntity.ok().body(sujets);
	}
	
	@PutMapping(value = "/editdesc/{sujetId}/{userId}/{desc}") 
	@ResponseBody
	public ResponseEntity<?> modifierDescription(@PathVariable("desc") String desc, 
									@PathVariable("sujetId") long sujetId,
									@PathVariable(value = "userId") Long userId){
		if(isujetservice.modifierDescription(desc, sujetId, userId)==0)
		return ResponseEntity.notFound().build();
	   return ResponseEntity.ok().build();			
	}
}

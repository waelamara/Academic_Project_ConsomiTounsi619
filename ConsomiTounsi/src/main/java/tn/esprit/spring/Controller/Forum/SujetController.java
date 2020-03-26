package tn.esprit.spring.Controller.Forum;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.Forum.ISujetService;
import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Forum.Sujet;


@RestController
@RequestMapping("/sujet")
public class SujetController {
  @Autowired
  ISujetService isujetservice;
  
  @PostMapping("/ajouter/{categId}/{userId}")
  public ResponseEntity<?> ajouterSujet( @PathVariable(value = "categId") Long categid,
		                              @PathVariable(value = "userId") Long userid,
		                              @Valid @RequestBody Sujet s ) {
    isujetservice.ajouterSujet(s, categid, userid);
	  return ResponseEntity.created(null).body(s);
	  
  }
	@GetMapping("/afficher")
	public List<Sujet> AfficherCategorie() {
		return isujetservice.getAllSujets();
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
	
	@PutMapping(value = "/editdesc/{id}/{desc}") 
	@ResponseBody
	public void modifierDescription(@PathVariable("desc") String desc, @PathVariable("id") long id) {
		isujetservice.modifierDescription(desc, id);
		
	}
}

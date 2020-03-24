package tn.esprit.spring.Controller.Forum;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity ajouterSujet( @PathVariable(value = "categId") Long categid,
		                              @PathVariable(value = "userId") Long userid,
		                              @Valid @RequestBody Sujet s ) {
    isujetservice.ajouterSujet(s, categid, userid);
	  return ResponseEntity.created(null).build();
	  
  }
	@GetMapping("/afficher")
	public List<Sujet> AfficherCategorie() {
		return isujetservice.getAllSujets();
	}
	
	@DeleteMapping("/delete/{id}/{userId}")
	public ResponseEntity<CategorieSujet> DeleteProduit(@PathVariable(value = "id") Long id,
														@PathVariable(value = "userId") Long userid) {
		Sujet s = isujetservice.findOne(id);
		if (s== null) {
			return ResponseEntity.notFound().build();
		}
		isujetservice.deleteSujetById(id, userid);
	return ResponseEntity.ok().build();
	}
	@GetMapping("/recherche/{nom}")
	public List<Sujet> findLikeNameM(@PathVariable(value = "nom") String name) {
		return isujetservice.findSujetbyName(name);
	}
	
	@GetMapping("/recherchecatg/{catgId}")
	public List<String> findNamebyCateg(@PathVariable(value = "catgId") Long catgId) {
		return isujetservice.getAllSujetNamesByCategorie(catgId);
	}
	@GetMapping("/rechercheuser/{userId}")
	public List<Sujet> findNamebyuser(@PathVariable(value = "userId") Long userId) {
		return isujetservice.findSujetbyUser(userId);
	}
}

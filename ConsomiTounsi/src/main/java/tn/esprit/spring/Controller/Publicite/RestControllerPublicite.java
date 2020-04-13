package tn.esprit.spring.Controller.Publicite;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Utils.AppConstants;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Service.Publicite.IPubliciteService;

@RestController
@RequestMapping("/publicite")
public class RestControllerPublicite {
	@Autowired
	IPubliciteService ipubliciteService;

	//http://localhost:8081/publicite/ajouter
	//{"nom":"Iphone","canal":"FACEBOOK","dateDebut":"2020-04-11","dateFin":"2020-05-11","nbrInitialVueCible":"100","nbrFinalVue":"500","cout":"30.500"}
	@PostMapping("/ajouter")
	public Publicite AjouterCategorie(@RequestParam(value = "pub", required = true) String ProduitJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) MultipartFile file) 
					throws JsonMappingException, JsonProcessingException, IOException {
		return ipubliciteService.Add(ProduitJson, file);
	}

	//http://localhost:8081/publicite/afficher
	@GetMapping("/afficher")
	public List<Publicite> AfficherCategorie() {
		return ipubliciteService.findAll();
	}

	//http://localhost:8081/publicite/delete/3
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteCategorie(@PathVariable(value = "id") Long idPub) {
		ipubliciteService.Delete(idPub);
		return ResponseEntity.ok("Supprimé avec succès");
	}

	//http://localhost:8081/publicite/edit/2
	//{"nom":"Iphone","canal":"FACEBOOK","dateDebut":"2020-04-11","dateFin":"2020-05-11","nbrInitialVueCible":"100","nbrFinalVue":"500","cout":"30.500"}
	@PutMapping("/edit/{id}")
	public ResponseEntity<Publicite> EditProduit(@PathVariable(value = "id") Long idPub,
			@Valid @RequestBody Publicite p) {
		Publicite PubUpdated = ipubliciteService.Update(p, idPub);
		return ResponseEntity.ok().body(PubUpdated);

	}

}

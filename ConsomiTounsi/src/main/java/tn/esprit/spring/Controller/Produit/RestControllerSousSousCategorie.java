package tn.esprit.spring.Controller.Produit;

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
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Model.Produit.SsCategorie;
import tn.esprit.spring.Service.Produit.ISousCategorieService;
import tn.esprit.spring.Service.Produit.ISousSousCategorieService;

@RestController
@RequestMapping("/SsCategorie")
public class RestControllerSousSousCategorie {
	@Autowired
	ISousCategorieService isousCategorieService;
	@Autowired
	ISousSousCategorieService isousSousCategorieService;

	@PostMapping("/ajouter/{idSCategorie}")
	public ResponseEntity<SsCategorie> AjouterSousCategorie(@PathVariable(value = "idSCategorie") Long idSCategorie,
			@Valid @RequestBody SsCategorie ssc) {
		SCategorie sc = isousCategorieService.findOne(idSCategorie);
		ssc.setIdSCategorie(sc);
		isousSousCategorieService.save(ssc);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/afficher")
	public List<SsCategorie> getAllProduit() {
		return isousSousCategorieService.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SsCategorie> DeleteProduit(@PathVariable(value = "id") Long idSscategorie) {
		SsCategorie ssc = isousSousCategorieService.findOne(idSscategorie);
		if (ssc == null) {
			return ResponseEntity.notFound().build();
		}
		isousSousCategorieService.Delete(ssc);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/edit/{id}/{idSCategorie}")
	public ResponseEntity<SsCategorie> EditProduit(@PathVariable(value = "id") Long idSscategorie,
			@PathVariable(value = "idSCategorie") Long idSCategorie, @Valid @RequestBody SsCategorie ssc) {
		SsCategorie ssc2 = isousSousCategorieService.findOne(idSscategorie);
		SCategorie sc = isousCategorieService.findOne(idSCategorie);
		if (ssc2 == null) {
			return ResponseEntity.notFound().build();
		}
		ssc2.setNomSsCategorie(ssc.getNomSsCategorie());
		ssc2.setIdSCategorie(sc);
		isousSousCategorieService.save(ssc2);
		return ResponseEntity.ok().build();

	}

}

package tn.esprit.spring.Controller;

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

import tn.esprit.spring.DAO.SousCategorieDAO;
import tn.esprit.spring.DAO.SousSousCategorieDAO;
import tn.esprit.spring.Model.GestionProduit.SCategorie;
import tn.esprit.spring.Model.GestionProduit.SsCategorie;

@RestController
@RequestMapping("/SsCategorie")
public class ControllerSousSousCategorie {
	@Autowired
	SousCategorieDAO sousCategorieDAO;
	@Autowired
	SousSousCategorieDAO sousSousCategorieDAO;
	
	@PostMapping("/ajouter/{idSCategorie}")
	public ResponseEntity<SsCategorie> AjouterSousCategorie(@PathVariable(value = "idSCategorie") Long idSCategorie,
			@Valid @RequestBody SsCategorie ssc) {
		SCategorie sc = sousCategorieDAO.findOne(idSCategorie);
		ssc.setIdSCategorie(sc);
		sousSousCategorieDAO.save(ssc);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/afficher")
	public List<SsCategorie> getAllProduit() {
		return sousSousCategorieDAO.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SsCategorie> DeleteProduit(@PathVariable(value = "id") Long idSscategorie) {
		SsCategorie ssc = sousSousCategorieDAO.findOne(idSscategorie);
		if (ssc == null) {
			return ResponseEntity.notFound().build();
		}
		sousSousCategorieDAO.Delete(ssc);
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("/edit/{id}/{idSCategorie}")
	public ResponseEntity<SsCategorie> EditProduit(@PathVariable(value = "id") Long idSscategorie,
			@PathVariable(value = "idSCategorie") Long idSCategorie, @Valid @RequestBody SsCategorie ssc) {
		SsCategorie ssc2 = sousSousCategorieDAO.findOne(idSscategorie);
		SCategorie sc = sousCategorieDAO.findOne(idSCategorie);
		if (ssc2 == null) {
			return ResponseEntity.notFound().build();
		}
		ssc2.setNomSsCategorie(ssc.getNomSsCategorie());
		ssc2.setIdSCategorie(sc);
		sousSousCategorieDAO.save(ssc2);
		return ResponseEntity.ok().build();

	}
	
}

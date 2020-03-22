package tn.esprit.spring.Controller.Forum;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Forum.ICategorieSujetService;
import tn.esprit.spring.Model.Forum.CategorieSujet;
;

@RestController
@RequestMapping("/categorieSujet")
public class CategorieSujetContoller {
	@Autowired
	 ICategorieSujetService  icategorieSujetService;
	
	@PostMapping("/ajouter")
	public int AjouterCategorie( @Valid @RequestBody CategorieSujet c) {
		return icategorieSujetService.ajouterCategorieSujet(c);
	}
}

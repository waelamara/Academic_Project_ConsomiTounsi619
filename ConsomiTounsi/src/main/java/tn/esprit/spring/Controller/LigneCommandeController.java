package tn.esprit.spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.LigneCommandeDao;
import tn.esprit.spring.Model.lignecommandeproduit;


@RestController
@RequestMapping("/Panier")
public class LigneCommandeController {
	@Autowired
	LigneCommandeDao ligneCommandeDao;
	@GetMapping("/{idUser}")
	public List<lignecommandeproduit> panierParIdclient(@PathVariable(value = "idUser") long id) {
	
		return ligneCommandeDao.panierParIdclient(id);
	}


}

package tn.esprit.spring.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.CommandeDAO;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Produit;

@RestController
@RequestMapping("/commande")
public class ControllerCommande {
	@Autowired
	CommandeDAO commandeDao;
	@PostMapping("/ajouter")
	public Commande AjouterCommande(@Valid @RequestBody Commande c)
	{
		return commandeDao.save(c);
	}

}

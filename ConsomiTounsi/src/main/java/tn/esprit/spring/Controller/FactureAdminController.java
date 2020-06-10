package tn.esprit.spring.Controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Service.Panier.FactureImpl;

@Controller(value = "FactureAdminController")
@ELBeanName(value = "FactureAdminController")
@Join(path = "/FactureAdmin", to = "/FactureAdmin.jsf")
public class FactureAdminController {
	@Autowired
	FactureImpl factureDAO;
	
	
	public List<Facture > getAllFacture(){
		
		return factureDAO.findAll();
		
	}
}

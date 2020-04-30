package tn.esprit.spring.Controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Service.Panier.CommandeImpl;


@Controller(value = "OrderAdminController")
@ELBeanName(value = "OrderAdminController")
@Join(path = "/OrderAdmin", to = "/Order.jsf")
public class OrderAdminController {
	@Autowired
	CommandeImpl CommandeDao;
public List<Commande> getAllCommande() {
		return CommandeDao.findAll();
	}
	

}

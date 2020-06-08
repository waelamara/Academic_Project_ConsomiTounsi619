package tn.esprit.spring.Controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Service.Panier.LigneCommandeImpl;

@Controller(value = "OrderStatistiqueController ")
@ELBeanName(value = "OrderStatistiqueController")
@Join(path = "/OrderStatistique", to = "/Order2.jsf")
public class OrderStatistiqueController {
	
	
	@Autowired
	LigneCommandeImpl ligneCommandeDao;
	
	public List<Object[]> NumCategorie()
	{
		
		return ligneCommandeDao.NumCategorie();
		
	}

}

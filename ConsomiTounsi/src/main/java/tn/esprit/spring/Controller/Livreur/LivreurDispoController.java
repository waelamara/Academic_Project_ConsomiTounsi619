package tn.esprit.spring.Controller.Livreur;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Repository.LivreurRepository;

@Controller(value = "LivreurDispoController")
@ELBeanName(value = "LivreurDispoController")
@Join(path = "/LivreurDispo", to = "/LivreurDispo.jsf")
public class LivreurDispoController {
	@Autowired
	LivreurRepository L;
	
	/*get all Livreur disponible*/
	public List<Livreur> getAlllivdispo(){
		
		return L.LivreursDispo("free");
		
	}

}

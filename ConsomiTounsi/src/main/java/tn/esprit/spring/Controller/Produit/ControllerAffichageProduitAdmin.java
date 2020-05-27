package tn.esprit.spring.Controller.Produit;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "ControllerAffichageProduitAdmin")
@ELBeanName(value = "ControllerAffichageProduitAdmin")
@Join(path = "/afficherProduit", to = "/pages/AffichageProduitAdmin.jsf")
public class ControllerAffichageProduitAdmin {
	  
 
	
}

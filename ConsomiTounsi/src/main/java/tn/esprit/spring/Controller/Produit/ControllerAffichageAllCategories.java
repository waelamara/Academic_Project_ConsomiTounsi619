package tn.esprit.spring.Controller.Produit;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "controllerAffichageAllCategories")
@ELBeanName(value = "controllerAffichageAllCategories")
@Join(path = "/AffichageCategories", to = "/pages/AffichageCategories.jsf")
public class ControllerAffichageAllCategories {
	
}

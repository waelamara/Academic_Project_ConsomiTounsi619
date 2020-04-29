package tn.esprit.spring.Controller.Produit;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "categorieController")
@ELBeanName(value = "categorieController")
@Join(path = "/shop/categorie", to = "/shop.jsf")
public class ControllerCategorie {

}

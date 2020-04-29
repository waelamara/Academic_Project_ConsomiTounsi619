package tn.esprit.spring.Controller.Produit;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "ScategorieController")
@ELBeanName(value = "ScategorieController")
@Join(path = "/shop/Scategorie", to = "/shop.jsf")
public class ControllerSousCategorie {

}

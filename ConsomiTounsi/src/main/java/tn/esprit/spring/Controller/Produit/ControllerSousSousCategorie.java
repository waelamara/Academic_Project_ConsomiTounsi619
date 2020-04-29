package tn.esprit.spring.Controller.Produit;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "SscategorieController")
@ELBeanName(value = "SscategorieController")
@Join(path = "/shop/Sscategorie", to = "/shop.jsf")
public class ControllerSousSousCategorie {

}

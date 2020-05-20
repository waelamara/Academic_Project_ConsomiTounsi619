package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "FactureController")
@ELBeanName(value = "factureController")
@Join(path = "/Facture", to = "/facture.jsf")
public class FactureController {

}

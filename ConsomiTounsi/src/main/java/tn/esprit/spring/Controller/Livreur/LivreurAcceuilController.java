package tn.esprit.spring.Controller.Livreur;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "LivreurAcceuilController")
@ELBeanName(value = "LivreurAcceuilController")
@Join(path = "/LivreurAcceuil", to = "/LivreurAcceuil.jsf")
public class LivreurAcceuilController {

}

package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "OrderStatistiqueController ")
@ELBeanName(value = "OrderStatistiqueController")
@Join(path = "/OrderStatistique", to = "/Order2.jsf")
public class OrderStatistiqueController {

}

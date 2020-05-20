package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "chekoutController")
@ELBeanName(value = "chekoutController")
@Join(path = "/Chekout", to = "/checkout2.jsf")
public class ChekoutController {

}

package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "cartController")
@ELBeanName(value = "cartController")
@Join(path = "/Cart", to = "/Cart.jsf")
public class CartController {

}

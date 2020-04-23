package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "shopController")
@ELBeanName(value = "shopController")
@Join(path = "/shop", to = "/shop.jsf")
public class ShopController {

}

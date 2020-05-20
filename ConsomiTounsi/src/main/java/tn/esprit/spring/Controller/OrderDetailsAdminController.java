package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "OrderDetailsAdminController")
@ELBeanName(value = "OrderDetailsAdminController")
@Join(path = "/OrderDetailsAdmin", to = "/OrderDetailsAdmin.jsf")
public class OrderDetailsAdminController {

}

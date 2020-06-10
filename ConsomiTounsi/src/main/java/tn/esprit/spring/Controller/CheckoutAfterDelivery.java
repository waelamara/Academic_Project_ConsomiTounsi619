package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "CheckoutAfterDelivery")
@ELBeanName(value = "CheckoutAfterDelivery")
@Join(path = "/CheckoutAfterDelivery", to = "/CheckoutAfterDelivery.jsf")
public class CheckoutAfterDelivery {

}

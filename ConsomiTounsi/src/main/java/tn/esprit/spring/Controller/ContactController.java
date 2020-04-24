package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "ContactController")
@ELBeanName(value = "ContactController")
@Join(path = "/Contact", to = "/Contact.jsf")
public class ContactController {

}

package tn.esprit.spring.Controller.Charite;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "ControllerAllPub")
@ELBeanName(value = "ControllerAllPub")
@Join(path = "/allpub", to = "/EventPub.jsf")
public class ControllerAllPub {

}

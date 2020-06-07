package tn.esprit.spring.Controller.Forum;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "ControllerPostAdmin")
@ELBeanName(value = "ControllerPostAdmin")
@Join(path = "/postadmin", to = "/fourm/postAdmin.jsf")
public class postAdmin {

}

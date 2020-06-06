package tn.esprit.spring.Controller.Publicite;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "ContollerAllpublicities")
@ELBeanName(value = "ContollerAllpublicities")
@Join(path = "/AllPublicites", to = "/pages/AllPublicities.jsf")
public class ContollerAllpublicities {

}

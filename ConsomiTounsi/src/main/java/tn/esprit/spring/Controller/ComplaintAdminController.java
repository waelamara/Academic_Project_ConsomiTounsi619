package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;


@Controller(value = "ComplaintAdminController")
@ELBeanName(value = "ComplaintAdminController")
@Join(path = "/showComplaint", to = "/ComplaintAdmin.jsf")
public class ComplaintAdminController {

}

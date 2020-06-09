package tn.esprit.spring.Controller.Rayon;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "VisitVirtuelJsf")
@ELBeanName(value = "VisitVirtuelJsf")
@Join(path = "/visitvirtuel", to = "/visitvirtuel.jsf")
public class VisitVirtuelJsf {
	
	
	

}

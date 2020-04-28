package tn.esprit.spring.Controller;


import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "dashController")
@ELBeanName(value = "dashController")
@Join(path = "/dash", to = "/dash.jsf")
public class DashController {

}

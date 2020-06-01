
package tn.esprit.spring.Controller.Rayon;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Service.Rayon.IRayonService;

@Controller(value = "JsfRayonController")
@ELBeanName(value = "JsfRayonController")
@Join(path = "/rayon", to = "rayon.jsf")
public class JsfRayonController {
	@Autowired
	IRayonService rayonDAO;

	
	
	
	public List<Rayon> getAllRayon() {
		return rayonDAO.getAllRayon();
	}
}

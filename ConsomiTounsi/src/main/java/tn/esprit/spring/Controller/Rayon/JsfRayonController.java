
package tn.esprit.spring.Controller.Rayon;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Model.Rayon.Type_rayon;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Service.Rayon.IRayonService;


@Controller(value = "JsfRayonController")
@ELBeanName(value = "JsfRayonController")
@Join(path = "/rayon", to = "rayon.jsf")
public class JsfRayonController {
	@Autowired
	IRayonService rayonDAO;

	
private String nom_rayon;
	
	
	@Enumerated(EnumType.STRING)
	private Type_rayon type_rayon;
	
	public Type_rayon[] getTypes() { return Type_rayon.values(); }
	
	
		
	
	public String getNom_rayon() {
		return nom_rayon;
	}

	public void setNom_rayon(String nom_rayon) {
		this.nom_rayon = nom_rayon;
	}

	public Type_rayon getType_rayon() {
		return type_rayon;
	}
	public void setType_rayon(Type_rayon type_rayon) {
		this.type_rayon = type_rayon;
	}


	public JsfRayonController() {
		super();
	}


	public JsfRayonController(String nom_rayon, Type_rayon type_rayon) {
		super();
		this.nom_rayon = nom_rayon;
		this.type_rayon = type_rayon;
	}








	public List<Rayon> getAllRayon() {
		return rayonDAO.getAllRayon();
	}
	
	
	public String saverayon() {
		String navigateTo = "/rayon.xhtml";
		 rayonDAO.saveRayon(new Rayon(nom_rayon,type_rayon));
		 return navigateTo;
		}

}

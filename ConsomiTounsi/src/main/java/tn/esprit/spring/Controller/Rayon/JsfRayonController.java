
package tn.esprit.spring.Controller.Rayon;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Model.Rayon.Type_rayon;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
import tn.esprit.spring.Service.Rayon.IRayonService;


@Controller(value = "JsfRayonController")
@ELBeanName(value = "JsfRayonController")
@Join(path = "/rayon", to = "/rayon.jsf")
public class JsfRayonController {
	@Autowired
	IRayonService rayonDAO;
	
	private Long idrayon;
	

	
public Long getidrayon() {
		return idrayon;
	}

	public void setidrayon(Long idrayon) {
		idrayon = idrayon;
	}

private String nom_rayon;

	
	@Enumerated(EnumType.STRING)
	private Type_rayon type_rayon;
	
	
	public Type_rayon[] getTypes() { return Type_rayon.values(); }
	
	private Long prodid;
	private String nomprod;
	
		
	
	public String getNomprod() {
		return nomprod;
	}

	public void setNomprod(String nomprod) {
		this.nomprod = nomprod;
	}

	public Long getProdid() {
		return prodid;
	}

	public void setProdid(Long prodid) {
		this.prodid = prodid;
	}

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

	
	public List<Produit> getAllProduitName() {
		return rayonDAO.getAllProduitName();
	}







	public List<Rayon> getAllRayon() {
		return rayonDAO.getAllRayon();
	}
	
	
	public String saverayon() {
		String navigateTo = "/rayon.xhtml";
		Rayon r=rayonDAO.saveRayon(new Rayon(nom_rayon,type_rayon));
		rayonDAO.affecterProduitARayon(r.getIdrayon(), prodid);;
		 return navigateTo;
		}
	@Transactional
	public void deleterayon(long Idrayon) {
		System.out.println("****************"+Idrayon);
		rayonDAO.deleteRayonById(Idrayon);
		System.out.println("****************"+Idrayon);
		

	}

}

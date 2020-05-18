package tn.esprit.spring.Controller.Produit;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Model.Produit.SsCategorie;
import tn.esprit.spring.Service.Produit.ISousSousCategorieService;

@Controller(value = "sscategorieController")
@ELBeanName(value = "sscategorieController")
@Getter
@Setter
public class ControllerSousSousCategorie {
	@Autowired
	ISousSousCategorieService iSousSousCategorieService;
	
	private Long id;
	private String nomSsCategorie;
	private SCategorie idSCategorie;
	private SsCategorie ssCategorie;
	
	public List<String> getAllSsousCategorie(){
		List<String> nomSscategorie=new ArrayList<>();
		for (SsCategorie ssc:iSousSousCategorieService.findAll()){
			nomSscategorie.add(ssc.getNomSsCategorie());
		}
		return nomSscategorie;
		
	}
	
	public List<SsCategorie> getAllSsousCategories(){
		return iSousSousCategorieService.findAll();
	}

	public SsCategorie getSsCategorie() {
		return ssCategorie;
	}

	public void setSsCategorie(SsCategorie ssCategorie) {
		this.ssCategorie = ssCategorie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomSsCategorie() {
		return nomSsCategorie;
	}

	public void setNomSsCategorie(String nomSsCategorie) {
		this.nomSsCategorie = nomSsCategorie;
	}

	public SCategorie getIdSCategorie() {
		return idSCategorie;
	}

	public void setIdSCategorie(SCategorie idSCategorie) {
		this.idSCategorie = idSCategorie;
	}

	
}

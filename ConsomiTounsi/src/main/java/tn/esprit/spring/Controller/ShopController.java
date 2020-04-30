package tn.esprit.spring.Controller;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Produit.Categorie;
import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Model.Produit.SsCategorie;
import tn.esprit.spring.Service.Produit.CategorieServiceImpl;
import tn.esprit.spring.Service.Produit.SousCategorieServiceImpl;
import tn.esprit.spring.Service.Produit.SousSousCategorieServiceImpl;

@Controller(value = "shopController")
@ELBeanName(value = "shopController")
@Join(path = "/shop", to = "/pages/shop.jsf")
public class ShopController {
	@Autowired
	CategorieServiceImpl categorieServiceImpl;
	@Autowired
	SousSousCategorieServiceImpl sousSousCategorieServiceImpl;
	@Autowired
	SousCategorieServiceImpl SousCategorieServiceImpl;
	
	private String nomSsCategorie;
	private String nomSCategorie;
	private String nomCategorie;
	private Long IdScategorie;
	private Long id;

	public List<Categorie> getAllCategorie(){
		//FacesContext fc = FacesContext.getCurrentInstance();
		//Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
//		
		//if(Integer.parseInt(params.get("filtrageProduit"))==0){
		return categorieServiceImpl.findAll();
		//}
		//else return null;
	}
//	public List<SCategorie> getAllSousCategorie(){
//		FacesContext fc = FacesContext.getCurrentInstance();
//		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
//		setFiltrageProduit(Integer.parseInt(params.get("filtrageProduit")));
//		if(filtrageProduit==0){
//		return SousCategorieServiceImpl.findAll();
//		}
//		else return null;
//	}
//	
//	public List<SsCategorie> getAllSousSousCategorie(){
//		FacesContext fc = FacesContext.getCurrentInstance();
//		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
//		setFiltrageProduit(Integer.parseInt(params.get("filtrageProduit")));
//		if(filtrageProduit==0){
//		return sousSousCategorieServiceImpl.findAll();
//		}
//		else return null;
//	}
	
	
	
	public List<SCategorie> getSousCategorie(Long id){
		return SousCategorieServiceImpl.findSCategorieByCategorie(id) ;	
	}
	
	public List<SsCategorie> getSousSousCategorie(Long id){
		return sousSousCategorieServiceImpl.findSsousCategorieByIdSCategorie(id) ;	
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public Long getIdScategorie() {
		return IdScategorie;
	}

	public void setIdScategorie(Long idScategorie) {
		IdScategorie = idScategorie;
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



	public String getNomSCategorie() {
		return nomSCategorie;
	}

	public void setNomSCategorie(String nomSCategorie) {
		this.nomSCategorie = nomSCategorie;
	}








}

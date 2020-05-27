package tn.esprit.spring.Controller.Produit;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Produit.Categorie;
import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Model.Produit.SsCategorie;
import tn.esprit.spring.Repository.Produit.CategorieRepository;
import tn.esprit.spring.Repository.Produit.SousCategorieRepository;
import tn.esprit.spring.Service.Produit.ICategorieService;
import tn.esprit.spring.Service.Produit.ISousCategorieService;
import tn.esprit.spring.Service.Produit.ISousSousCategorieService;

@Controller(value = "AdminFormProduit")
@ELBeanName(value = "AdminFormProduit")
@Join(path = "/ajouterProduit", to = "/pages/AdminFormProduit.jsf")
public class AdminFormProduit {
	@Autowired
	ICategorieService iCategorieService;
	@Autowired
	ISousCategorieService iSousCategorieService;
	@Autowired
	SousCategorieRepository sousCategorieRepository;
	@Autowired
	CategorieRepository CategorieRepository;
	@Autowired
	ISousSousCategorieService iSousSousCategorieService;
	
	private Long id;
	private String nomCategorie;
	private String nomSCategorie;
	private String nomSsCategorie;
	private List <String> listNomScateg;
	private List <String> listNomSsouscateg;
	
	
	public List<String> getAllCategorieName(){
		List<String> nomCategorie=new ArrayList<>();
		for (Categorie c:iCategorieService.findAll()){
			nomCategorie.add(c.getNomCategorie());
		}
		return nomCategorie;
	}

	public List<String> getAllSCategorieName(){
		List<String> nomSCategorie=new ArrayList<>();
		Categorie categorie=CategorieRepository.findCategorieByName(nomCategorie);	
			for (SCategorie sc:iSousCategorieService.findSCategorieByCategorie(categorie.getId())){
			nomSCategorie.add(sc.getNomSCategorie());
		}
		return nomSCategorie;
	}
	
	public List<String> getAllSsCategorieName(){
		List<String> nomSsCategorie=new ArrayList<>();
		SCategorie Scategorie=sousCategorieRepository.findSCategorieByName(nomSCategorie);	
			for (SsCategorie ssc:iSousSousCategorieService.findSsousCategorieByIdSCategorie(Scategorie.getId())){
				nomSsCategorie.add(ssc.getNomSsCategorie());
		}
		return nomSsCategorie;
	}
	
	
	public List<Categorie> getAllCategories(){
		return iCategorieService.findAll();
	}
	
	
	 public void onCountryChange() {
	        if(nomCategorie !=null && !nomCategorie.equals(""))
	        	listNomScateg = getAllSCategorieName();
	        else
	        	listNomScateg = new ArrayList<String>(); ;
	        	
	        if(nomSCategorie !=null && !nomSCategorie.equals(""))
	        	listNomSsouscateg = getAllSsCategorieName();
		    else
		    	listNomSsouscateg = new ArrayList<String>(); ;
	    }
	
	
	
	
	
	public String getNomSsCategorie() {
		return nomSsCategorie;
	}

	public void setNomSsCategorie(String nomSsCategorie) {
		this.nomSsCategorie = nomSsCategorie;
	}

	public List<String> getListNomSsouscateg() {
		return listNomSsouscateg;
	}

	public void setListNomSsouscateg(List<String> listNomSsouscateg) {
		this.listNomSsouscateg = listNomSsouscateg;
	}

	public String getNomSCategorie() {
		return nomSCategorie;
	}

	public void setNomSCategorie(String nomSCategorie) {
		this.nomSCategorie = nomSCategorie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public List<String> getListNomScateg() {
		return listNomScateg;
	}

	public void setListNomScateg(List<String> listNomScateg) {
		this.listNomScateg = listNomScateg;
	}

	
	
	
	
	
	
	
}

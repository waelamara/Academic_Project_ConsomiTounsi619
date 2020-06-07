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

@Controller(value = "categorieController")
@ELBeanName(value = "categorieController")
@Join(path = "/Formcategorie", to = "/pages/CategorieForm.jsf")
public class ControllerCategorie {
	@Autowired
	ICategorieService iCategorieService;
	@Autowired
	ISousCategorieService iSousCategorieService;
	@Autowired
	CategorieRepository CategorieRepository;
	@Autowired
	ISousSousCategorieService iSousSousCategorieService;
	@Autowired
	SousCategorieRepository sousCategorieRepository;
	
	private Long id;
	private String nomCategorie;
	private String nomCategorieForAddSCategorie;
	private String nomCategorieSearch;
	private String nomSCategorie;
	private String nomSsCategorie;
	private List <String> listNomScateg;
	
	
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
	
	public List<String> getAllSCategorieNameForSScategorie(){
		List<String> nomSCategorie=new ArrayList<>();
		Categorie categorie=CategorieRepository.findCategorieByName(nomCategorieSearch);	
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
	
	public List<SCategorie> getAllSCategories(){
		return iSousCategorieService.findAll();
	}
	
	
	public List<SCategorie> getSCategories(){
		if(nomCategorie !=null && !nomCategorie.equals("")){
		Categorie categorie=CategorieRepository.findCategorieByName(nomCategorie);	
		return iSousCategorieService.findSCategorieByCategorie(categorie.getId());
		}
		else return iSousCategorieService.findAll();
	}
	
	

	
	 public void onCategorieChange() {
	        if(nomCategorieSearch !=null && !nomCategorieSearch.equals("")){
	        	 listNomScateg= getAllSCategorieNameForSScategorie();
	        }
	        else
	        	listNomScateg = new ArrayList<String>();

	    }
	
	public List<SsCategorie> getSsCategories(){
		if(nomSCategorie !=null && !nomSCategorie.equals("")){
			SCategorie scategorie=sousCategorieRepository.findSCategorieByName(nomSCategorie);
			return iSousSousCategorieService.findSsousCategorieByIdSCategorie(scategorie.getId());
		}
		
		return iSousSousCategorieService.findAll();
	}
	
	
	
	
	public void removeCategorie(Long Id){
		iCategorieService.Delete(Id);
	}
	
	public void removeSCategorie(Long Id){
		iSousCategorieService.Delete(Id);
	}
	
	public void removeSSCategorie(Long Id){
		iSousSousCategorieService.Delete(Id);
	}
	
	
	public String AddCategories(){
		iCategorieService.save(new Categorie(nomCategorie));
		this.nomCategorie=null;
		this.nomCategorieSearch=null;
		return "null";
	}
	
	public String AddSCategories(){
		Categorie categorie=CategorieRepository.findCategorieByName(nomCategorie);
		SCategorie scategorie=new SCategorie(nomSCategorie);
		iSousCategorieService.save(scategorie, categorie.getId());
		this.nomCategorie=null;
		this.nomSCategorie=null;
		this.nomCategorieSearch=null;
		return "null";
		
	}
	
	public String AddSsCategories(){
		SCategorie scategorie=sousCategorieRepository.findSCategorieByName(nomSCategorie);
		SsCategorie sscategorie=new SsCategorie(nomSsCategorie);
		iSousSousCategorieService.save(sscategorie, scategorie.getId());
		this.nomCategorie=null;
		this.nomSCategorie=null;
		this.nomSsCategorie=null;
		this.nomCategorieSearch=null;
		return "null";	
	}
	
	
	
	
	public void onNameCategorieChange(){
		System.out.println(nomCategorie);
	}
	
	
	

	public String getNomCategorieForAddSCategorie() {
		return nomCategorieForAddSCategorie;
	}

	public void setNomCategorieForAddSCategorie(String nomCategorieForAddSCategorie) {
		this.nomCategorieForAddSCategorie = nomCategorieForAddSCategorie;
	}

	public String getNomCategorieSearch() {
		return nomCategorieSearch;
	}


	public void setNomCategorieSearch(String nomCategorieSearch) {
		this.nomCategorieSearch = nomCategorieSearch;
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

	public String getNomSCategorie() {
		return nomSCategorie;
	}

	public void setNomSCategorie(String nomSCategorie) {
		this.nomSCategorie = nomSCategorie;
	}

	public String getNomSsCategorie() {
		return nomSsCategorie;
	}

	public void setNomSsCategorie(String nomSsCategorie) {
		this.nomSsCategorie = nomSsCategorie;
	}


	public List<String> getListNomScateg() {
		return listNomScateg;
	}

	public void setListNomScateg(List<String> listNomScateg) {
		this.listNomScateg = listNomScateg;
	}


	
	
	
}

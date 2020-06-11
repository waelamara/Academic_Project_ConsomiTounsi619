package tn.esprit.spring.Controller.Produit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Produit.Categorie;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Model.Produit.SsCategorie;
import tn.esprit.spring.Repository.Produit.CategorieRepository;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
import tn.esprit.spring.Repository.Produit.SousCategorieRepository;
import tn.esprit.spring.Service.Produit.ICategorieService;
import tn.esprit.spring.Service.Produit.IProduitService;
import tn.esprit.spring.Service.Produit.ISousCategorieService;
import tn.esprit.spring.Service.Produit.ISousSousCategorieService;

@Controller(value = "ControllerProduit")
@ELBeanName(value = "ControllerProduit")
@Join(path = "/produit", to = "/pages/produit.jsf")
public class ControllerProduit {
	@Autowired
	IProduitService iproduitService;
	@Autowired
	ICategorieService iCategorieService;
	@Autowired
	ISousCategorieService iSousCategorieService;
	@Autowired
	ISousSousCategorieService iSousSousCategorieService;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	AdminFormProduit adminFormProduit;
	@Autowired
	ControllerSousSousCategorie controllerSousSousCategorie;
	@Autowired
	SousCategorieRepository sousCategorieRepository;
	@Autowired
	CategorieRepository CategorieRepository;
	
	private Long id;
	private String nomProduit;
	private float prix;
	private String description;
	public  Long barcode;
	private float poids;
	private float prixAchat;
	private int filtrageProduit;
	private Long idFiltrageProduit;
	private UploadedFiles files;
	private List<Produit> listRechercheProduits;
	private String nomRechercheProduit;
	private String nomRechercheProduitInShopPage="";
	private Long idToUpdate;
	private String nomCategorie;
	private String nomSCategorie;
	private String nomSsCategorie;
	private List <String> listNomScateg;
	private List <String> listNomSsouscateg;
	private String barcodeSearch;
	private SsCategorie idSsCategorie;
	private int quantiteProduitdeMoisVendu;
	private List <Produit> recentlyViewed=new ArrayList<Produit>();
	
	public List<Produit> getProduitsByCategorie(Long idCategorie) {
		return iproduitService.findProduitCategorie(idCategorie);
	}

	public List<Produit> getProduitsBySCategorie(Long idSCategorie) {
		return iproduitService.findProduitSCategorie(idSCategorie);
	}

	public List<Produit> getProduitsBySsCategorie(Long idSsCategorie) {
		return iproduitService.findProduitSsCategorie(idSsCategorie);
	}

	@Transactional
	public String nameRecherche() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		setFiltrageProfuit(Integer.parseInt(params.get("filtrageProduit")));
		setIdFiltrageProfuit(Long.parseLong(params.get("idRecherhceProduit")));
		if (filtrageProduit == 0) {
			return iCategorieService.findOne(idFiltrageProduit).getNomCategorie();
		} else if (filtrageProduit == 1) {
			return iSousCategorieService.findOne(idFiltrageProduit).getNomSCategorie();
		} else if (filtrageProduit == 2) {
			return iSousSousCategorieService.findOne(idFiltrageProduit).getNomSsCategorie();
		} else
			return null;
	}

	public Produit getOneProduit() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		Produit produit=produitRepository.findProduit(Long.parseLong(params.get("idProduit")));
		String existant="NONEXISTANT";
		if(recentlyViewed.size()==6){
			recentlyViewed.remove(0);
		}
		for(Produit p:recentlyViewed){
			if (p.getId()==produit.getId()) existant="EXISTANT";
		}
		if(existant.equals("NONEXISTANT")){
			recentlyViewed.add(produit);
		}

		
		return produit;
	}

	public List<Produit> getProduits() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		setFiltrageProfuit(Integer.parseInt(params.get("filtrageProduit")));
		setIdFiltrageProfuit(Long.parseLong(params.get("idRecherhceProduit")));

//		if (filtrageProduit == 0) {
//			return iproduitService.findProduitCategorie(idFiltrageProduit);
//		} else if (filtrageProduit == 1) {
//			return iproduitService.findProduitSCategorie(idFiltrageProduit);
//		} else if (filtrageProduit == 2) {
//			return iproduitService.findProduitSsCategorie(idFiltrageProduit);
//		} else
//			return null;
		if (filtrageProduit == 0) {
			return iproduitService.findProduitCategorieAndName(idFiltrageProduit,nomRechercheProduitInShopPage);
		} else if (filtrageProduit == 1) {
			return iproduitService.findProduitSCategorieAndName(idFiltrageProduit,nomRechercheProduitInShopPage);
		} else if (filtrageProduit == 2) {
			return iproduitService.findProduitSsCategorieAndName(idFiltrageProduit, nomRechercheProduitInShopPage);
		} else
			return null;
	}
	
	public List<Produit> getAllProduits() {
		 if((nomRechercheProduit==null||nomRechercheProduit.equals(""))&&(nomCategorie==null||nomCategorie.equals(""))&&(nomSCategorie==null||nomSCategorie.equals(""))&&(nomSsCategorie==null||nomSsCategorie.equals(""))&&(barcodeSearch==null||barcodeSearch.equals(""))) {
			return iproduitService.findAll();
			}
		else if(nomRechercheProduit!=null&&(nomCategorie==null||nomCategorie.equals(""))&&(nomSCategorie==null||nomSCategorie.equals(""))&&(nomSsCategorie==null||nomSsCategorie.equals(""))&&(barcodeSearch==null||barcodeSearch.equals(""))) {
			   return findLikeName(nomRechercheProduit);
			}
		else if((nomRechercheProduit==null||nomRechercheProduit.equals(""))&&(nomCategorie!=null)&&(nomSCategorie==null||nomSCategorie.equals(""))&&(nomSsCategorie==null||nomSsCategorie.equals(""))&&(barcodeSearch==null||barcodeSearch.equals(""))) {
			   return iproduitService.findProduitCategorie(CategorieRepository.findCategorieByName(nomCategorie).getId());
			}
		else if((nomRechercheProduit==null||nomRechercheProduit.equals(""))&&(nomSCategorie!=null)&&(nomSsCategorie==null||nomSsCategorie.equals(""))&&(barcodeSearch==null||barcodeSearch.equals(""))) {
			   return iproduitService.findProduitSCategorie(sousCategorieRepository.findSCategorieByName(nomSCategorie).getId());
			}
		else if((nomRechercheProduit==null||nomRechercheProduit.equals(""))&&(nomSsCategorie!=null)&&(barcodeSearch==null||barcodeSearch.equals(""))) {
			   return iproduitService.findProduitSsCategorie(iSousSousCategorieService.findSsCategorieByName(nomSsCategorie).getId());
			}
		else if((nomRechercheProduit==null||nomRechercheProduit.equals(""))&&(nomCategorie==null||nomCategorie.equals(""))&&(nomSCategorie==null||nomSCategorie.equals(""))&&(nomSsCategorie==null||nomSsCategorie.equals(""))&&(barcodeSearch!=null)){
			return iproduitService.findProduitByBarcode(barcodeSearch);
		}

		
		else{
			return iproduitService.findAll();
		}
		
	}

	
	
	public String addProduit() {
	if(idToUpdate!=null){

		iproduitService.UpdateProduit(new Produit(idToUpdate ,nomProduit,  prix,  description,  barcode,  poids,  prixAchat));
		idToUpdate=null;
		HideProduits();
		return "/pages/AffichageProduitAdmin.xhtml?faces-redirect=true";
		}
	else {
			iproduitService.addProduitWithImage(new Produit( nomProduit,  prix,  description,  barcode,  poids,  prixAchat),files);
			HideProduits();
			return "/pages/AffichageProduitAdmin.xhtml?faces-redirect=true";
		}
		
	}
	
	
	public List<Produit> findLikeName(String name){
	 return iproduitService.findLikeName(name);
	}
	
	public void RemoveProduit(Long id){
		iproduitService.Delete(id);
	}
	
	public void HideProduits(){
		this.idToUpdate=null;
		this.nomProduit=null;
		this.description=null;
		this.prix=0;
		this.poids=0;
		this.prixAchat=0;
		this.barcode=null;
		this.nomSsCategorie=null;
	}
	
	public String DisplayProduits(Produit p){
		adminFormProduit.setNomCategorie(p.getIdSsCategorie().getIdSCategorie().getIdCategorie().getNomCategorie());
		adminFormProduit.setNomSCategorie(p.getIdSsCategorie().getIdSCategorie().getNomSCategorie());
		adminFormProduit.onCountryChange();
		controllerSousSousCategorie.setNomSsCategorie(p.getIdSsCategorie().getNomSsCategorie());
		this.idToUpdate=p.getId();
		this.nomProduit=p.getNomProduit();
		this.description=p.getDescription();
		this.prix=p.getPrix();
		this.poids=p.getPoids();
		this.prixAchat=p.getPrixAchat();
		this.barcode=p.getBarcode();
		
		return "/pages/AdminFormProduit.xhtml?faces-redirect=true";
	}
	
	
	
	
	public List<Produit> MostPopularProductsOfMonth() {
		List<Produit> listProduct=iproduitService.MostPopularProductsOfMonth();
		listProduct.remove(0);
		return listProduct;

	}
	
	public Produit ProductOfMonth() {
		List<Produit> listProduct=iproduitService.MostPopularProductsOfMonth();
		
			if(listProduct!=null){
				this.quantiteProduitdeMoisVendu=iproduitService.QuantiteProduitdeMoisVendu(listProduct.get(0).getId());
				return listProduct.get(0);
			}
			else return new Produit();

	}
	
	
	public List<Produit> NewProductsAdded() {
		List<Produit> listProduct=iproduitService.findAll();
		listProduct.remove(listProduct.size()-1);
			if(listProduct.size()>=21){
				return listProduct.subList(listProduct.size()-21, listProduct.size());

			}
			
			 return listProduct;

	}
	
	public Produit NewProductAdded() {
		List<Produit> listProduct=iproduitService.findAll();
		
			if(listProduct!=null){
				return listProduct.get(listProduct.size()-1);
			}
			else return new Produit();

	}
	
	public List<Produit> MostPopularCategorieProducts() {
		return iproduitService.MostPopularCategorieProducts(iCategorieService.MostPopularCategorie().getId());
	}
	
	public Categorie MostPopularCategorie() {
		return iCategorieService.MostPopularCategorie();
	}
	
	public String barcodeImage(){
		System.out.println(barcode.toString().substring(0, 13));
		return barcode.toString().substring(0, 13);
	}
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	
	
	public UploadedFiles getFiles() {
		return files;
	}

	public void setFiles(UploadedFiles files) {
		this.files = files;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}





	public Long getBarcode() {
		return barcode;
	}

	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	public int getFiltrageProfuit() {
		return filtrageProduit;
	}

	public void setFiltrageProfuit(int filtrageProduit) {
		this.filtrageProduit = filtrageProduit;
	}

	public Long getIdFiltrageProfuit() {
		return idFiltrageProduit;
	}

	public void setIdFiltrageProfuit(Long idFiltrageProduit) {
		this.idFiltrageProduit = idFiltrageProduit;
	}

	public List<Produit> getListRechercheProduits() {
		return listRechercheProduits;
	}

	public void setListRechercheProduits(List<Produit> listRechercheProduits) {
		this.listRechercheProduits = listRechercheProduits;
	}

	public String getNomRechercheProduit() {
		return nomRechercheProduit;
	}

	public void setNomRechercheProduit(String nomRechercheProduit) {
		this.nomRechercheProduit = nomRechercheProduit;
	}

	public Long getIdToUpdate() {
		return idToUpdate;
	}

	public void setIdToUpdate(Long idToUpdate) {
		this.idToUpdate = idToUpdate;
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

	public List <String> getListNomScateg() {
		return listNomScateg;
	}

	public void setListNomScateg(List <String> listNomScateg) {
		this.listNomScateg = listNomScateg;
	}

	public List <String> getListNomSsouscateg() {
		return listNomSsouscateg;
	}

	public void setListNomSsouscateg(List <String> listNomSsouscateg) {
		this.listNomSsouscateg = listNomSsouscateg;
	}

	public String getBarcodeSearch() {
		return barcodeSearch;
	}

	public void setBarcodeSearch(String barcodeSearch) {
		this.barcodeSearch = barcodeSearch;
	}

	public SsCategorie getIdSsCategorie() {
		return idSsCategorie;
	}

	public void setIdSsCategorie(SsCategorie idSsCategorie) {
		this.idSsCategorie = idSsCategorie;
	}

	public int getQuantiteProduitdeMoisVendu() {
		return quantiteProduitdeMoisVendu;
	}

	public void setQuantiteProduitdeMoisVendu(int quantiteProduitdeMoisVendu) {
		this.quantiteProduitdeMoisVendu = quantiteProduitdeMoisVendu;
	}

	public List <Produit> getRecentlyViewed() {
		return recentlyViewed;
	}

	public void setRecentlyViewed(List <Produit> recentlyViewed) {
		this.recentlyViewed = recentlyViewed;
	}

	public String getNomRechercheProduitInShopPage() {
		return nomRechercheProduitInShopPage;
	}

	public void setNomRechercheProduitInShopPage(String nomRechercheProduitInShopPage) {
		this.nomRechercheProduitInShopPage = nomRechercheProduitInShopPage;
	}
	
	
	

}

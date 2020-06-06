package tn.esprit.spring.Controller.Produit;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
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
	private Long id;
	private String nomProduit;
	private float prix;
	private String description;
	private Long barcode;
	private float poids;
	private float prixAchat;
	private int filtrageProduit;
	private Long idFiltrageProduit;
	private UploadedFiles files;
	private List<Produit> listRechercheProduits;
	private String nomRechercheProduit;
	private Long idToUpdate;

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
		return produitRepository.findProduit(Long.parseLong(params.get("idProduit")));
	}

	public List<Produit> getProduits() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		setFiltrageProfuit(Integer.parseInt(params.get("filtrageProduit")));
		setIdFiltrageProfuit(Long.parseLong(params.get("idRecherhceProduit")));
		if (filtrageProduit == 0) {
			return iproduitService.findProduitCategorie(idFiltrageProduit);
		} else if (filtrageProduit == 1) {
			return iproduitService.findProduitSCategorie(idFiltrageProduit);
		} else if (filtrageProduit == 2) {
			return iproduitService.findProduitSsCategorie(idFiltrageProduit);
		} else
			return null;
	}
	
	public List<Produit> getAllProduits() {
		if(nomRechercheProduit==null){
			return iproduitService.findAll();
		}
		else{
		   return findLikeName(nomRechercheProduit);
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
			return "null";
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
	
	
	

}

package tn.esprit.spring.Controller.Produit;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Service.Produit.IProduitService;

@Controller(value = "ControllerProduit")
@ELBeanName(value = "ControllerProduit")
public class ControllerProduit {
	@Autowired
	IProduitService iproduitService;
	private Long id;
	private String nomProduit;
	private float prix;
	private String description;
	private Long barcode;
	private float poids;
	private float prixAchat;
	
	public List<Produit> getProduitsByCategorie(Long idCategorie){
		return iproduitService.findProduitCategorie(idCategorie);
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
	
	
}

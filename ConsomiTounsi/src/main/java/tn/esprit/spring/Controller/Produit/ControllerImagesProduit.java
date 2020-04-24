package tn.esprit.spring.Controller.Produit;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Produit.ImageProduit;
import tn.esprit.spring.Service.Produit.IImageProduitService;

@Controller(value = "ControllerImagesProduit")
@ELBeanName(value = "ControllerImagesProduit")
public class ControllerImagesProduit {

	@Autowired
	IImageProduitService imageProduitService;
	
	private Long id;
	private String image;
	
	public List<ImageProduit> getImageProduits(Long idProduit){
		return imageProduitService.findImageProduits(idProduit);
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}

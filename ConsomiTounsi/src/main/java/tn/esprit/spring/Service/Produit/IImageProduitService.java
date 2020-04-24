package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.ImageProduit;

public interface IImageProduitService {
	public ImageProduit save(ImageProduit image);
	public List<ImageProduit> findImageProduits(Long idProduit);
}

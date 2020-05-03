package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.ImageProduit;

public interface IImageProduitService {
	public ImageProduit save(ImageProduit image);
	public ImageProduit findImageProduits(Long idProduit);
	public List<ImageProduit> findAllImagesProduits(Long idProduit);
}

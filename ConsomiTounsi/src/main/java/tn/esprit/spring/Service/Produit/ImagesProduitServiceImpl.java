package tn.esprit.spring.Service.Produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.ImageProduit;
import tn.esprit.spring.Repository.Produit.ImagesProduitRepository;

@Service
public class ImagesProduitServiceImpl implements IImageProduitService {
	@Autowired
	ImagesProduitRepository imagesProduitInterface;

	public ImageProduit save(ImageProduit image) {
		return imagesProduitInterface.save(image);
	}
	
	
	
}

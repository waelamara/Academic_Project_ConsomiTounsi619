package tn.esprit.spring.Service.Produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.ImageProduit;
import tn.esprit.spring.Repository.Produit.ImagesProduitRepository;

@Service
public class ImagesProduitServiceImpl implements IImageProduitService {
	@Autowired
	ImagesProduitRepository imagesProduitRepository;

	public ImageProduit save(ImageProduit image) {
		return imagesProduitRepository.save(image);
	}

	public ImageProduit findImageProduits(Long idProduit) {
		return imagesProduitRepository.findImageProduits(idProduit);
	}

	@Override
	public List<ImageProduit> findAllImagesProduits(Long idProduit) {
		return imagesProduitRepository.findAllImagesProduits(idProduit);
	}
	

	
	
}

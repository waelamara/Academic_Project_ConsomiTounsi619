package tn.esprit.spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.GestionProduit.ImageProduit;
import tn.esprit.spring.Repository.ImagesProduitInterface;

@Service
public class ImagesProduitDAO {
	@Autowired
	ImagesProduitInterface imagesProduitInterface;

	public ImageProduit save(ImageProduit image) {
		return imagesProduitInterface.save(image);

	}
}

package tn.esprit.spring.Repository.Produit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Produit.ImageProduit;


public interface ImagesProduitRepository extends JpaRepository<ImageProduit, Long> {

	@Query(value = "SELECT * FROM image_produit  WHERE idproduit_id=?1", nativeQuery = true)
	public ImageProduit findImageProduits(Long idProduit);
	@Query(value = "SELECT * FROM image_produit  WHERE idproduit_id=?1", nativeQuery = true)
	public List<ImageProduit> findAllImagesProduits(Long idProduit);
}

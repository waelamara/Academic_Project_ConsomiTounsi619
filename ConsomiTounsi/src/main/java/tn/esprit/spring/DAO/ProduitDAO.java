package tn.esprit.spring.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.GestionProduit.Produit;
import tn.esprit.spring.Repository.ProduitRepository;

@Service
public class ProduitDAO  {
	@Autowired
	ProduitRepository produitRepository;

	public Produit save(Produit p) {
		return produitRepository.save(p);
	}

	public List<Produit> findAll() {
		return produitRepository.findAll();
	}

	public void Delete(Produit p) {
		produitRepository.delete(p);
	}

	public Produit findOne(Long id) {
		return produitRepository.getOne(id);
	}

	public List<Produit> findLikeName(String name) {
		return produitRepository.findLikeName(name);

	}
	public List<Produit> findProduitSsCategorie(Long idSsCategorie) {
		return produitRepository.findProduitSsCategorie(idSsCategorie);
	}
	public List<Produit> findProduitSCategorie(Long idSCategorie) {
		return produitRepository.findProduitSCategorie(idSCategorie);
	}
	public List<Produit> findProduitCategorie(Long idCategorie) {
		return produitRepository.findProduitCategorie(idCategorie);
	}

}

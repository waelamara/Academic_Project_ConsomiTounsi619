package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.Produit;

public interface IProduitService {
	public Produit save(Produit p);
	public void Delete(Produit p);
	public List<Produit> findAll();
	public Produit findOne(Long id);
	public List<Produit> findLikeName(String name);
	public List<Produit> findProduitSsCategorie(Long idSsCategorie);
	public List<Produit> findProduitSCategorie(Long idSCategorie);
	public List<Produit> findProduitCategorie(Long idCategorie);
}

package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.Categorie;

public interface ICategorieService {
	public Categorie save(Categorie c);
	public List<Categorie> findAll();
	public void Delete(Categorie c);
	public Categorie findOne(Long id);
}

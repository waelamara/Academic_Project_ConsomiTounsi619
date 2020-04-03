package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.Categorie;

public interface ICategorieService {
	public Categorie save(Categorie c);
	public Categorie Update(Categorie c,Long id);
	public List<Categorie> findAll();
	public void Delete(Long id);
	public Categorie findOne(Long id);
}

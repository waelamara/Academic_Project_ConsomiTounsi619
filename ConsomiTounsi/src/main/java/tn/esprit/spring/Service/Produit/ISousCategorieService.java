package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.SCategorie;

public interface ISousCategorieService {
	public SCategorie save(SCategorie c);
	public List<SCategorie> findAll();
	public SCategorie findOne(Long id);
	public void Delete(SCategorie sc);
}

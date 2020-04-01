package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.SsCategorie;

public interface ISousSousCategorieService {
	public SsCategorie save(SsCategorie c);
	public List<SsCategorie> findAll();
	public SsCategorie findOne(Long id);
	public void Delete(SsCategorie sc);
}

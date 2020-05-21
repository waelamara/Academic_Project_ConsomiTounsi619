package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.SsCategorie;

public interface ISousSousCategorieService {
	public SsCategorie save(SsCategorie ssc,Long idSCategorie);
	public List<SsCategorie> findAll();
	public SsCategorie findOne(Long id);
	public void Delete(Long id);
	public SsCategorie Update(SsCategorie ssc,Long idSscategorie,Long idSCategorie);
	public String GetNameById(Long id);
	public List<SsCategorie> findSsousCategorieByIdSCategorie(Long id);
}

package tn.esprit.spring.Service.Produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Model.Produit.SsCategorie;
import tn.esprit.spring.Repository.Produit.SousSousCategorieRepository;

@Service
public class SousSousCategorieServiceImpl implements ISousSousCategorieService {
	@Autowired
	SousSousCategorieRepository sousSousCategorieRepository;
	@Autowired
	ISousCategorieService iSousCategorieService;

	public SsCategorie save(SsCategorie ssc, Long idSCategorie) {
		SCategorie sc = iSousCategorieService.findOne(idSCategorie);
		ssc.setIdSCategorie(sc);
		return sousSousCategorieRepository.save(ssc);
	}

	public List<SsCategorie> findAll() {
		return sousSousCategorieRepository.findAll();
	}

	public SsCategorie findOne(Long id) {
		return sousSousCategorieRepository.getOne(id);
	}
	public List<SsCategorie> findSsousCategorieByIdSCategorie(Long id){
		return sousSousCategorieRepository.findByIdSCategorie(id);
	}

	public void Delete(Long id) {
		SsCategorie ssc = findOne(id);
		sousSousCategorieRepository.delete(ssc);
	}

	public SsCategorie Update(SsCategorie ssc, Long idSscategorie, Long idSCategorie) {
		SsCategorie ssc2 = findOne(idSscategorie);
		SCategorie sc = iSousCategorieService.findOne(idSCategorie);
		ssc2.setNomSsCategorie(ssc.getNomSsCategorie());
		ssc2.setIdSCategorie(sc);
		return sousSousCategorieRepository.save(ssc2);
	}

	@Override
	public String GetNameById(Long id) {
		String name=sousSousCategorieRepository.getOne(id).getNomSsCategorie();
		return name;
	}

	@Override
	public SsCategorie findSsCategorieByName(String name) {
		return sousSousCategorieRepository.findSsCategorieByName(name) ;
	}
}

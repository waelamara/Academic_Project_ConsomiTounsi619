package tn.esprit.spring.Service.Produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.Categorie;
import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Repository.Produit.SousCategorieRepository;

@Service
public class SousCategorieServiceImpl implements ISousCategorieService {
	@Autowired
	SousCategorieRepository sousCategorieRepository;
	@Autowired
	ICategorieService iCategorieService;

	public SCategorie save(SCategorie sc, Long idCategorie) {
		Categorie c = iCategorieService.findOne(idCategorie);
		sc.setIdCategorie(c);
		return sousCategorieRepository.save(sc);
	}

	public List<SCategorie> findAll() {
		return sousCategorieRepository.findAll();
	}

	public SCategorie findOne(Long id) {
		return sousCategorieRepository.getOne(id);
	}
	
	public List<SCategorie> findSCategorieByCategorie(Long id){
	return  sousCategorieRepository.findByIdCategorie(id);
			
	}
	public void Delete(Long id) {
		SCategorie sc = findOne(id);
		sousCategorieRepository.delete(sc);
	}

	public SCategorie Update(SCategorie sc, Long idScategorie, Long idCategorie) {
		SCategorie sc2 = findOne(idScategorie);
		Categorie c = iCategorieService.findOne(idCategorie);
		sc2.setNomSCategorie(sc.getNomSCategorie());
		sc2.setIdCategorie(c);
		return sousCategorieRepository.save(sc2);
	}

	@Override
	public String GetNameById(Long id) {
		String name=sousCategorieRepository.getOne(id).getNomSCategorie();
		return name;
	}
}

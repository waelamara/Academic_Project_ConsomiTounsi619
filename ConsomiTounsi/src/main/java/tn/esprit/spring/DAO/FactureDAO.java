package tn.esprit.spring.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Repository.FactureRepository;

@Service
public class FactureDAO {
@Autowired
FactureRepository factureRepository;
public Facture findOne(Long id) {
	return factureRepository.getOne(id);
}
public List<Facture> findAll() {
	return factureRepository.findAll();
}


public Facture  save ( Facture f)
{
	f.setDate(LocalDate.now());
	return factureRepository.save(f);
}
public void Delete(Facture f) {
	factureRepository.delete(f);
}

}
package tn.esprit.spring.DAO.Charite;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Charite.Charite;

public interface ChariteDAO {
	boolean saveCharit(Charite Charite);
	List<Charite> getAllChariteList();
	public int saveCharite(Long idevents,Long iduser,Charite Charite);
	Charite findOne(Long id);
	public int saveCharite1(Long idevents,Long iduser,Charite Charite);

	public List<Commande> getCommande(Long idCommande);
	public Optional<Commande> findCommandeById(Long idCommande);

	
}

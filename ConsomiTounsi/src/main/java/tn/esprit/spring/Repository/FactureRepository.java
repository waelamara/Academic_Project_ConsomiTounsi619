package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.lignecommandeproduit;

public interface FactureRepository extends JpaRepository<Facture, Long> {
	
	@Query(value = "SELECT  NEW tn.esprit.spring.Model.lignecommandeproduit(f.date,p.NomProduit,l.quantity,p.Prix,l.quantity*p.Prix,u.firstName,c.montant) FROM LigneCommande l join l.commande c  join l.produit p join c.factureid f join c.idUser u  WHERE c.idUser.id=:idc ")
	public List<lignecommandeproduit> FactureParIdUser(@Param("idc")long i);
	
}
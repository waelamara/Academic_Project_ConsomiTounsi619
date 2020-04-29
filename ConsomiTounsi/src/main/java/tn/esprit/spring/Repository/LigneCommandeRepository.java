package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;







@Repository

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

	@Query(value = "SELECT  NEW tn.esprit.spring.Model.lignecommandeproduit(l.id,p.id,p.nomProduit,l.quantity,p.prix,l.quantity*p.prix,c.montant) FROM LigneCommande l join l.commande c  join l.produit p   WHERE c.idUser.id=:idc and c.status='en cours'")
	public List<lignecommandeproduit> panierParIdclient(@Param("idc")long i);
	
	
	@Query(value = "SELECT * FROM ligne_commande l JOIN commande c on l.commande_id=c.id  WHERE l.produit_id=?1 AND c.id_user=?2 AND l.status='en cours' and c.id !=?3 ", nativeQuery = true)
	public LigneCommande findLigneCommande(Long idProduit,Long idClient,Long idCommande);
	
	
	@Query(value = "SELECT * FROM ligne_commande l JOIN commande c on l.commande_id=c.id  WHERE l.produit_id=?1 AND c.id_user=?2 AND c.status='en cours'", nativeQuery = true)
	public LigneCommande findLigneCommande(Long idProduit,Long idClient);
	
	
	@Query(value = "SELECT COUNT(*) as n ,c.nom_categorie,SUM(l.price*l.quantity) as t FROM categorie c JOIN scategorie sc on c.id=sc.id_categorie_id join ss_categorie ssc on sc.id=ssc.idscategorie_id "
			+ "join produit p on ssc.id=p.id_ss_categorie_id join ligne_commande l ON p.id=l.produit_id GROUP BY c.id ORDER BY t DESC ", nativeQuery = true)
	public List<Object[]>NumCategorie();
	
	
	@Query(value = "SELECT COUNT(*) as n FROM produit p JOIN ligne_commande l on p.id=l.produit_id JOIN commande c on l.commande_id =c.id WHERE p.id=?1 and c.status='payee'", nativeQuery = true)
	public int NumProduitVendu(Long idProduit);

}

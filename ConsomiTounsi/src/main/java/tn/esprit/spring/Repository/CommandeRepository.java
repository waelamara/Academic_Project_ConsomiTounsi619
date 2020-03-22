package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Model.Commande;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	@Query(value = "SELECT * FROM commande WHERE typede_payment=?1", nativeQuery = true)
	public List<Commande> CommandeparType(String string);
	@Query(value = "SELECT * FROM commande WHERE id_user=?1", nativeQuery = true)
	public List<Commande> CommandeparClient(int id);
	@Query(value = "SELECT NEW tn.esprit.spring.Model.Commande(c.id,c.date,c.montant,c.status)FROM LigneCommande l join  l.commande c join l.produit p WHERE p.Barcode=:code")
	public List<Commande> Commandeparcode(@Param("code")int code);


}


package tn.esprit.spring.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "SELECT user_id FROM user WHERE  point_fidelite >=100 ", nativeQuery = true)
	public List<String> findClient_pt_100();

	/* liste des livreur */
	@Query(value = "SELECT user_id FROM user_roles WHERE role_id=4", nativeQuery = true)
	public List<Long> Listettlivreur();

	@Modifying
	@Query(value = "UPDATE `user` SET `etat`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ConfirmerLiv(String etat1, long id);
	
	@Query(value = "SELECT `user_id` FROM `user` WHERE `etat`=?1 and `disponible`=?2 and `lieux_travail`=?3 ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public long findparhasard(String etat,String dispo,String lieuxTravail);	
	


}

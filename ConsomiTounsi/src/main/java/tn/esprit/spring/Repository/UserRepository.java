
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

	/* liste des users */
	@Query(value = "SELECT user_id FROM user_roles WHERE role_id=1", nativeQuery = true)
	public List<Long> ListeUsers();
	
	@Modifying
	@Query(value = "UPDATE `user` SET `etat`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ConfirmerLiv(String etat1, long id);
	
	@Query(value = "SELECT `user_id` FROM `user` WHERE `etat`=?1 and `disponible`=?2 and `lieux_travail`=?3 ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public long findparhasard(String etat,String dispo,String lieuxTravail);	
	
	/* Selon Sexe */
	@Query(value = "SELECT COUNT(*) FROM user WHERE `sexe`=?1", nativeQuery = true)
	public int NombreUsersSelonSexe(String Sexe);
	/* Change disponibilité livreur */
	@Modifying
	@Query(value = "UPDATE `user` SET `disponible`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ChangeDispo(String Dispo, long id);
	//Top 10 Stat liv
	@Query(value = "SELECT `user_id` FROM `user`  ORDER BY `nb_mission` DESC LIMIT 10", nativeQuery = true)
	public List<Long> Top10nbLivreur();
	
	@Query(value = "SELECT * FROM `user` WHERE `username` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonChoix(String cle);
	@Query(value = "SELECT * FROM `user` WHERE `email` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonEmail(String cle);
	
	
    //SELECT `user_id` FROM `user`  ORDER BY `nb_mission` DESC LIMIT 10

}

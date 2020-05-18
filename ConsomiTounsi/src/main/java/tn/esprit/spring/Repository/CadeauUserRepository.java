package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Model.CadeauUser;

@Repository
public interface CadeauUserRepository extends JpaRepository<CadeauUser, Long> {
	
	@Query(value = "SELECT id FROM `cadeau_user`where validite=0 and montant BETWEEN 0 AND 299 ", nativeQuery = true)
	public List<String> idCadeauMax300();
	
	
	@Query(value = "SELECT id FROM `cadeau_user`where validite=0 and montant BETWEEN 0 AND 499 ", nativeQuery = true)
	public List<String> idCadeauMax500();
	
	
	@Query(value = "SELECT id FROM `cadeau_user`where validite=0 and montant BETWEEN 0 AND 1000", nativeQuery = true)
	public List<String> idCadeauMax1000();
	
	
	@Query(value = "SELECT 	montant FROM `cadeau_user`where code=?1 ", nativeQuery = true)
	public float montantCadeau(String code);
	
	
	@Query(value = "SELECT 	* FROM `cadeau_user`where code=?1 and id_user=?2 and validite=0 ", nativeQuery = true)
	public CadeauUser verifierCode(String code,long idUser);
	
	
	@Query(value = "SELECT 	count(*) FROM `cadeau_user`where validite=0", nativeQuery = true)
	public float nombreCodeValidee();

	@Query(value = "SELECT 	* FROM `cadeau_user`where  id_user=?1 and validite=0 ", nativeQuery = true)
	public CadeauUser verifier(long idUser);
	
}

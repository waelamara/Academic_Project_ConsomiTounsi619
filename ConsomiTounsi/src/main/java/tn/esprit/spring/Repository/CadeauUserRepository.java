package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.CadeauUser;


public interface CadeauUserRepository extends JpaRepository<CadeauUser, Long> {
	@Query(value = "SELECT id FROM `cadeau_user`where validite=0 and montant BETWEEN 0 AND 299 ", nativeQuery = true)
	public List<String> idCadeauMax300();
	@Query(value = "SELECT id FROM `cadeau_user`where validite=0 and montant BETWEEN 0 AND 499 ", nativeQuery = true)
	public List<String> idCadeauMax500();
	@Query(value = "SELECT id FROM `cadeau_user`where validite=0 and montant BETWEEN 0 AND 1000", nativeQuery = true)
	public List<String> idCadeauMax1000();
	@Query(value = "SELECT 	montant FROM `cadeau_user`where code=?1 ", nativeQuery = true)
	public float montantCadeau(String code);
	

}

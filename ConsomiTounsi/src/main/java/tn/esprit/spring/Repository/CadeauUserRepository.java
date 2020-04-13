package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.CadeauUser;


public interface CadeauUserRepository extends JpaRepository<CadeauUser, Long> {
	@Query(value = "SELECT id FROM `cadeau_user`where validite=0 ", nativeQuery = true)
	public List<String> idCadeau();
	@Query(value = "SELECT 	montant FROM `cadeau_user`where code=?1 ", nativeQuery = true)
	public float montantCadeau(String code);
	

}


package tn.esprit.spring.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.Model.User;

public interface UserRepository extends JpaRepository <User, Long> {
	Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);


	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value = "SELECT user_id FROM user WHERE  point_fidelite >=100 ", nativeQuery = true)
	public List<String> findClient_pt_100();
	
}

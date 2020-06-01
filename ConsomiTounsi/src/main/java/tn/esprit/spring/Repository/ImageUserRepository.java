package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Model.ImageUser;

@Repository
public interface ImageUserRepository extends JpaRepository<ImageUser, Long> {
	@Query(value = "SELECT * FROM image_user WHERE user_id_user_id=?1", nativeQuery = true)
	public ImageUser findImageUser(Long idUser);

}

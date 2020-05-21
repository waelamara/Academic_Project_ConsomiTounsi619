package tn.esprit.spring.Repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Forum.Vus;
public interface VusRepository extends JpaRepository<Vus, Long> {
	@Query(value="select * from vus where id_sujet=?1 AND id_user=?2",nativeQuery=true)
	public Vus getVusBySujetAndUser(Long sujetId, Long userId);
	@Query(value = "select sum(nb_vus) from vus where  id_sujet= ?1", nativeQuery = true)
	 public Integer countVus(Long sujetId);

}

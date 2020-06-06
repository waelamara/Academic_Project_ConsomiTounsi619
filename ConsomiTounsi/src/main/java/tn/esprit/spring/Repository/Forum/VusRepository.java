<<<<<<< HEAD
package tn.esprit.spring.Repository.Forum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Forum.Vus;
public interface VusRepository extends JpaRepository<Vus, Long> {
	@Query(value="select * from vus where id_sujet=?1 AND id_user=?2",nativeQuery=true)
	public Vus getVusBySujetAndUser(Long sujetId, Long userId);
	@Query(value = "select sum(nb_vus) from vus where  id_sujet= ?1", nativeQuery = true)
	 public Integer countVus(Long sujetId);
	@Query(value="select id_sujet from vus where MONTH(date_ajout)=MONTH(NOW())and YEAR(date_ajout)=YEAR(NOW()) GROUP BY id_sujet ORDER by  sum(nb_vus) DESC LIMIT 4",nativeQuery = true)
	public List <Long> mostidsujetviews();
}
=======
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
>>>>>>> branch 'master' of https://github.com/waelamara/ConsomiTounsi619.git

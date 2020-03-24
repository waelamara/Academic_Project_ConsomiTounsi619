package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.Publicite.Publicite;

public interface PubliciteRepository extends JpaRepository<Publicite, Long> {

}

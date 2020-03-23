package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.User;

public interface UserRepository extends JpaRepository <User, Long> {

}

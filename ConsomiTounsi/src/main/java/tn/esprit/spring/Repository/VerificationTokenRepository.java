package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

    VerificationToken findByToken(String token);
 
    VerificationToken findByUser(User user);

}

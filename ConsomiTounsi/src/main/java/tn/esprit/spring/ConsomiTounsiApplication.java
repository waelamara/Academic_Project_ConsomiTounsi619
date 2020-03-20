package tn.esprit.spring;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.bytebuddy.asm.Advice.Local;
import tn.esprit.spring.DAO.CommandeDAO;
import tn.esprit.spring.Model.Commande;

@SpringBootApplication
public class ConsomiTounsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsomiTounsiApplication.class, args);
	
	}

}

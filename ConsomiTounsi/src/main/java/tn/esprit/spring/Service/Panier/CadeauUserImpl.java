package tn.esprit.spring.Service.Panier;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.CadeauUser;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.CadeauUserRepository;
import tn.esprit.spring.Repository.UserRepository;

@Service
public class CadeauUserImpl implements ICadeauUser {
	
	@Autowired
	CadeauUserRepository cadeauUserRepository;
	@Autowired
	UserRepository userRepository;
	public void save ()
	{
		for(int i=0;i<=50;i++)
		{
			CadeauUser c = new CadeauUser();
	   c.setMontant((int) (Math.random() * (1000 - 0 )));
	   c.setCode(givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect());

		
		 cadeauUserRepository.save(c);
		}
		
		
	}

		
		
	

	public String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 8;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    return generatedString;
	}
	public String CadeauUser(Long idUser)
	{
		 
		 List<String> pp = cadeauUserRepository.idCadeau();
			
			String delim2 = ",";
			String res2 = String.join(delim2, pp);
			String motcommentaire2[] = res2.split(",");
			int r2 = (int) (Math.random() * (motcommentaire2.length));
			String name2 = motcommentaire2[r2];
			long random = Long.parseLong(name2);
			CadeauUser cd = cadeauUserRepository.getOne(random);
			//cd.getMontant();
			User u = userRepository.getOne(idUser);
			if(cd.getMontant()>u.getPointFidelite())
			{
			cd.setMontant((int) (cd.getMontant()-u.getPointFidelite()));
			cd.setIdUser(u);
			u.setPointFidelite(0);
			cd.setValidite(true);
			userRepository.save(u);
			cadeauUserRepository.save(cd);
			}
			else
			{
			u.setPointFidelite(u.getPointFidelite()-(int) (cd.getMontant()));
			cd.setIdUser(u);
			cd.setValidite(true);
			userRepository.save(u);
			cadeauUserRepository.save(cd);
			}
			return cd.getCode();
				
			
	}
	
	public float montantCadeau(String code)
	{
		return cadeauUserRepository.montantCadeau(code);
	}
	


}

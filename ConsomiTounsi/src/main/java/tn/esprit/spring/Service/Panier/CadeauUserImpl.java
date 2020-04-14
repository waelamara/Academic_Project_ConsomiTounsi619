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
	public long random(List<String> pp )
	{
		long random=0;
		String delim2 = ",";
		String res2 = String.join(delim2, pp);
		String motcommentaire2[] = res2.split(",");
		int r2 = (int) (Math.random() * (motcommentaire2.length));
		String name2 = motcommentaire2[r2];
		if(name2.equals("")) 
			random=0;
		else
		 random = Long.parseLong(name2);
		return random;
	}
	
	public String CadeauUser(Long idUser)
	{
	
	String a="";
		
			User u = userRepository.getOne(idUser);
		
			
			if (u.getPointFidelite()<=299)
			{
				long random1 =  random(cadeauUserRepository.idCadeauMax300());
				if(random1==0){a="malheureusement vous n'avez pas gagné";}
				else
				{
				CadeauUser cd1 = cadeauUserRepository.getOne(random1);
				cd1.setIdUser(u);
				if(cd1.getMontant()>u.getPointFidelite()){u.setPointFidelite(0);}
				else {u.setPointFidelite((int) (u.getPointFidelite()-cd1.getMontant()));}
				cd1.setValidite(true);
				userRepository.save(u);
				cadeauUserRepository.save(cd1);
				 a= cd1.getCode();
				}
			}
			else if ((u.getPointFidelite()>=300)&&(u.getPointFidelite()<=499))
			{
				long random2 =  random(cadeauUserRepository.idCadeauMax500());
				if(random2==0){a="malheureusement vous n'avez pas gagné";}
				else{
				CadeauUser cd2 = cadeauUserRepository.getOne(random2);
				cd2.setIdUser(u);
				if(cd2.getMontant()>u.getPointFidelite()){u.setPointFidelite(0);}
				else {u.setPointFidelite((int) (u.getPointFidelite()-cd2.getMontant()));}
				cd2.setValidite(true);
				userRepository.save(u);
				cadeauUserRepository.save(cd2);
				 a= cd2.getCode();
				}
			}
			else if((u.getPointFidelite()>=500))
			{
				long random3 =  random(cadeauUserRepository.idCadeauMax1000());
				if(random3==0){a="malheureusement vous n'avez pas gagné";}
				else
				{
				CadeauUser cd3 = cadeauUserRepository.getOne(random3);
				cd3.setIdUser(u);
				
				if(cd3.getMontant()>u.getPointFidelite()){u.setPointFidelite(0);}
				else {u.setPointFidelite((int) (u.getPointFidelite()-cd3.getMontant()));}
				cd3.setValidite(true);
				userRepository.save(u);
				cadeauUserRepository.save(cd3);
				 a= cd3.getCode();
				}
			}

		
				return a;
			
	}
	/*if(cd.getMontant()>u.getPointFidelite())
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
	}*/
	
	public float montantCadeau(String code)
	{
		return cadeauUserRepository.montantCadeau(code);
	}
	


}

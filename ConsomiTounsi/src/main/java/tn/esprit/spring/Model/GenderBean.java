package tn.esprit.spring.Model;

import javax.annotation.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean
public class GenderBean {
	 public Sexe[] getSexe() {
	        return Sexe.values();
	    }
}

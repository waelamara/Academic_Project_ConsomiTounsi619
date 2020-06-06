package tn.esprit.spring.Service.Charite;

import java.util.List;

import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;

public interface EndroitDAO {
	List<Endroit> getAllEndroitList();
	List<Endroit> getAllEndroitDi();
	List<Endroit> getAllEndroitR();
	public int saveEndroit1(Events e,Endroit Endroit);
	Endroit findOne(Long id);
	Endroit saveEndroit1(Endroit Endroit);
	//public String reserveEndroit( Endroit e,Long idevent);
	public int saveEndroit(Long ideventss, Endroit Endroit);
	public int saveEndroit(Long ideventss,Long idendroit,Endroit Endroit);
	public int saveEndroit2(Long idendroit, Endroit e);
	List<Endroit> getAllEndroitEv(Long id);
	
	
}

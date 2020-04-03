package tn.esprit.spring.Service.Publicite;

import java.util.List;

import tn.esprit.spring.Model.Publicite.Publicite;

public interface IPubliciteService {
	public Publicite save(Publicite p);
	public List<Publicite> findAll();
	public Publicite Update(Publicite p,Long id);
	public void Delete(Long id);
	public Publicite findOne(Long id);
	
}

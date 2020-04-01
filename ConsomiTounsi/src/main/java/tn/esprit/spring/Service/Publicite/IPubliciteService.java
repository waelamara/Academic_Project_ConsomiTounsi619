package tn.esprit.spring.Service.Publicite;

import java.util.List;

import tn.esprit.spring.Model.Publicite.Publicite;

public interface IPubliciteService {
	public Publicite save(Publicite p);
	public List<Publicite> findAll();
	public void Delete(Publicite c);
	public Publicite findOne(Long id);
}

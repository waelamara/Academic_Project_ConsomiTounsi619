package tn.esprit.spring.Service.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.Vus;

public interface IVusService {
	
	public int ajouterVus(Vus v,Long sujetId,Long userId);
	public void UpdateVus(Long sujetId, Long userId);
	public int countVus(Long sujetId);   
	public Boolean verificationVus(Long userId, Long sujetId) ;
	public List<Long> mostPopularPost();
}

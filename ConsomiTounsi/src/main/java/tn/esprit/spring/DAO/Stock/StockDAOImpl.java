package tn.esprit.spring.DAO.Stock;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.Repository.Stock.StockRepository;

public class StockDAOImpl {
	@Autowired
StockRepository stockRepository;
}

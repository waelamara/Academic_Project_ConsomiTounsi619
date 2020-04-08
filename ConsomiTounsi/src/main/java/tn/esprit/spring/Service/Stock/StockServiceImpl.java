package tn.esprit.spring.Service.Stock;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.Repository.Stock.StockRepository;

public class StockServiceImpl {
	@Autowired
StockRepository stockRepository;
}

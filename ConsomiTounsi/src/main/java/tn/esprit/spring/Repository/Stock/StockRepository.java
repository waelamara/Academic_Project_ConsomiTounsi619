package tn.esprit.spring.Repository.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.Stock.Stock;

public interface StockRepository extends JpaRepository <Stock, Long>  {

}

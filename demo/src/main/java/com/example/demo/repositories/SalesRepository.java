package com.example.demo.repositories;

import com.example.demo.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//This interface communicates with the database and has a method for searching the sales records
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findSalesById (long kw);
}

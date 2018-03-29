package com.maat.bestbuy.integration.repository;


import com.maat.bestbuy.integration.model.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, String> {

}

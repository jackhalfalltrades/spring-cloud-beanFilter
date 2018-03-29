package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ApplicationsRepository extends JpaRepository<Application, String> {

}

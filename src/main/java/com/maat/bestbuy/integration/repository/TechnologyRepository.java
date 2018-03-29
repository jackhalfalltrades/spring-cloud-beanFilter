package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, String> {

}

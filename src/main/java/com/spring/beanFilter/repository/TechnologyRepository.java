package com.spring.beanFilter.repository;

import com.spring.beanFilter.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, String> {

}

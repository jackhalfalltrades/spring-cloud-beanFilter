package com.spring.beanFilter.repository;


import com.spring.beanFilter.model.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, String> {

}

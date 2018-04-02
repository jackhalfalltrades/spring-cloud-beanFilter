package com.spring.beanFilter.repository;

import com.spring.beanFilter.model.ServerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerTypeRepository extends JpaRepository<ServerType, String> {

}

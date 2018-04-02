package com.spring.beanFilter.repository;

import com.spring.beanFilter.model.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeRepository extends JpaRepository<Mode, String> {

}

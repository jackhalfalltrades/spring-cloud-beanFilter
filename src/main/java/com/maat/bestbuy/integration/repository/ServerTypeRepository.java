package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.ServerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerTypeRepository extends JpaRepository<ServerType,String> {

}

package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeRepository extends JpaRepository<Mode, String> {

}

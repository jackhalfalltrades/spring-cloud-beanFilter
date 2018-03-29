package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Integer> {

}

package com.spring.beanFilter.repository;

import com.spring.beanFilter.model.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Integer> {

}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MachineOption;

@Repository
public interface MachineOptionRepository extends JpaRepository<MachineOption, Long> {
    void deleteByMachineName(String machineName);

    void deleteByMachineNameIgnoreCase(String option);

    boolean existsByMachineNameIgnoreCase(String option);
}


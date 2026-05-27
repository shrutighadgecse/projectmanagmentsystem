package com.example.demo.repository;

import com.example.demo.model.MachineProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineProjectRepository extends JpaRepository<MachineProject, Long> {
    Optional<MachineProject> findByCcn(String ccn);
}
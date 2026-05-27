package com.example.demo.service;

import com.example.demo.model.Machine;
import com.example.demo.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAll() {
        return machineRepository.findAll();
    }

    public Optional<Machine> getByCcn(String ccn) {
        return machineRepository.findByCcn(ccn);
    }

    public Machine save(Machine machine) {
        return machineRepository.save(machine);
    }

    public List<Object[]> getLiveStatusSummary() {
        return machineRepository.countByLiveStatus();
    }

    public List<Machine> getAllOrderByTimestampDesc() {
        return machineRepository.findAllOrderByTimestampDesc();
    }
}

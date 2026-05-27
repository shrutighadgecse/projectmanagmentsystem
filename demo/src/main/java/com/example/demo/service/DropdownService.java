package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Assembly;
import com.example.demo.model.KMLocationOption;
import com.example.demo.model.LMLocationOption;
import com.example.demo.model.MachineOption;
import com.example.demo.repository.AssemblyRepository;
import com.example.demo.repository.KMLocationOptionRepository;
import com.example.demo.repository.LMLocationOptionRepository;
import com.example.demo.repository.MachineOptionRepository;
@Service
public class DropdownService {

    @Autowired
    private MachineOptionRepository machineOptionRepository;

    @Autowired
    private LMLocationOptionRepository lmLocationOptionRepository;

    
    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private KMLocationOptionRepository kmLocationOptionRepository;


    // Get all options for Machine Name
    public List<String> getMachineOptions() {
        return machineOptionRepository.findAll().stream()
                .map(option -> option.getMachineName().toUpperCase())
                .collect(Collectors.toList());
    }

    // Add new option for Machine Name if not already present
    public void addMachineOption(String option) {
        if (!machineOptionRepository.existsByMachineNameIgnoreCase(option)) {
            machineOptionRepository.save(new MachineOption(option.toUpperCase()));
        }
    }

    // Delete option for Machine Name
    public void deleteMachineOption(String option) {
        machineOptionRepository.deleteByMachineNameIgnoreCase(option);
    }

    // Get all options for Assy Location
    public List<String> getLMLocationOptions() {
        return lmLocationOptionRepository.findAll().stream()
                .map(option -> option.getLMLocation().toUpperCase())
                .collect(Collectors.toList());
    }

    // Add new option for Assy Location if not already present
    public void addLMLocationOption(String option) {
        if (!lmLocationOptionRepository.existsByLmLocationIgnoreCase(option)) {
            lmLocationOptionRepository.save(new LMLocationOption(option.toUpperCase()));
        }
    }

    // Delete option for Assy Location
    public void deleteLMLocationOption(String option) {
        lmLocationOptionRepository.deleteByLmLocationIgnoreCase(option);
    }


        // Get all options for AssemblyCode

    public List<String> getAssemblyCode() {
        return assemblyRepository.findAll().stream()
        .map(option -> option.getAssemblycode().toUpperCase())
        .collect(Collectors.toList());    }

            // Add new option for AssemblyCode if not already present

    public void addAssemblyCode(String option) {
        if (!assemblyRepository.existsByAssemblyCodeIgnoreCase(option)) {
            assemblyRepository.save(new Assembly(option.toUpperCase()));
        }    }

            // Delete option for AssemblyCode

    public void deleteAssemblyCode(String option) {
        assemblyRepository.deleteByAssemblyCodeIgnoreCase(option);
    }


      // Get all options for Assy Location
      public List<String> getKMLocationOptions() {
        return kmLocationOptionRepository.findAll().stream()
                .map(option -> option.getKMLocationOption().toUpperCase())
                .collect(Collectors.toList());
    }

    // Add new option for Assy Location if not already present
    public void addKMLocationOption(String option) {
        if (!kmLocationOptionRepository.existsByKmLocationIgnoreCase(option)) {
            kmLocationOptionRepository.save(new KMLocationOption(option.toUpperCase()));
        }
    }

    // Delete option for Assy Location
    public void deleteKMLocationOption(String option) {
        kmLocationOptionRepository.deleteByKmLocationIgnoreCase(option);
    }

}

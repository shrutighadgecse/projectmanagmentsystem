package com.example.demo.service;

import com.example.demo.entity.MachineModel;
import com.example.demo.repository.MachineModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MachineModelService {

    @Autowired
    private MachineModelRepository machineModelRepository;

    public MachineModel saveMachineModel(MachineModel machineModel) {
        return machineModelRepository.save(machineModel);
    }

    public List<String> getSuggestions(String field, String query) {
        System.out.println("Fetching all models for suggestions");
        List<MachineModel> models = machineModelRepository.findAll();
        System.out.println("Models fetched: " + models);
        return models.stream()
                .map(model -> {
                    switch (field) {
                        case "machineName": return model.getMachineName();
                        case "machineModel": return model.getMachineModel();
                        default: return "";
                    }
                })
                .filter(value -> value != null && value.toLowerCase().contains(query.toLowerCase()))
                .distinct()
                .collect(Collectors.toList());
    }

    public void addNewValue(String field, String value) {
        MachineModel model = new MachineModel();
        switch (field) {
            case "machineName": model.setMachineName(value); break;
            case "machineModel": model.setMachineModel(value); break;
        }
        machineModelRepository.save(model);
    }

    public List<String> getModelsByMachine(String machineName) {
        List<MachineModel> models = machineModelRepository.findAll();
        return models.stream()
            .filter(model -> model.getMachineName() != null && model.getMachineName().equalsIgnoreCase(machineName))
            .map(MachineModel::getMachineModel)
            .filter(modelName -> modelName != null && !modelName.isEmpty())
            .distinct()
            .collect(Collectors.toList());
    }

    public Optional<MachineModel> findByMachineModel(String machineModel) {
        return machineModelRepository.findByMachineModel(machineModel);
    }
}
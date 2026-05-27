package com.example.demo.controller;

import com.example.demo.entity.MachineModel;
import com.example.demo.service.MachineModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machine-model")
public class MachineModelController {

    @Autowired
    private MachineModelService machineModelService;

    @PostMapping
    public MachineModel createMachineModel(@RequestBody MachineModel machineModel) {
        return machineModelService.saveMachineModel(machineModel);
    }

    @GetMapping("/suggestions")
    public List<String> getSuggestions(@RequestParam String field, @RequestParam String q) {
        System.out.println("Fetching suggestions for field: " + field + " with query: " + q);
        List<String> suggestions = machineModelService.getSuggestions(field, q);
        System.out.println("Suggestions fetched: " + suggestions);
        return suggestions;
    }

    @PostMapping("/add-new")
    public void addNewValue(@RequestParam String field, @RequestParam String value) {
        machineModelService.addNewValue(field, value);
    }

    @GetMapping("/models-by-machine")
    public List<String> getModelsByMachine(@RequestParam String machineName) {
        return machineModelService.getModelsByMachine(machineName);
    }

    @GetMapping("/total-process-task")
    public Integer getTotalProcessTaskByModel(@RequestParam String model) {
        return machineModelService.findByMachineModel(model)
                .map(MachineModel::getTotalProcessTask)
                .orElse(null);
    }

    @GetMapping("/std-task")
    public Integer getStdTaskByModel(@RequestParam String model) {
        return machineModelService.findByMachineModel(model)
                .map(MachineModel::getTotalProcessTask)
                .orElse(null);
    }
}
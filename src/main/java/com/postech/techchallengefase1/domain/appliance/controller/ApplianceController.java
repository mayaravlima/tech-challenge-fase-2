package com.postech.techchallengefase1.domain.appliance.controller;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.appliance.service.ApplianceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

    private final ApplianceService service;

    public ApplianceController(ApplianceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Appliance> save(@Valid @RequestBody Appliance appliance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAppliance(appliance));
    }

    @GetMapping
    public ResponseEntity<Set<Appliance>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllAppliance());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appliance> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getApplianceById(id));
    }

    @PutMapping
    public ResponseEntity<Appliance> update(@Valid @RequestBody Appliance appliance) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAppliance(appliance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
        service.deleteApplianceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Appliance deleted successfully"));
    }

}

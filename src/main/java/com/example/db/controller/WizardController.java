package com.example.db.controller;

import com.example.db.character.Wizard;
import com.example.db.port.in.WizardPort;
import com.example.db.service.WizardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wizard")
public class WizardController implements WizardPort {

    private final WizardService wizardService;

    @GetMapping
    @Override
    public ResponseEntity<List<Wizard>> getWizards() {
        return ResponseEntity.ok(wizardService.getWizards());
    }

    @PostMapping
    @Override
    public ResponseEntity<Wizard> create(@RequestBody Wizard wizard) {
        return ResponseEntity.ok().body(wizardService.createWizard(wizard.getName()));
    }

    @PatchMapping("/{id}/status")
    @Override
    public ResponseEntity<Wizard> changeStatusWizard(@PathVariable String id) {
        return ResponseEntity.ok(wizardService.changeStatusWizard(id));
    }
}

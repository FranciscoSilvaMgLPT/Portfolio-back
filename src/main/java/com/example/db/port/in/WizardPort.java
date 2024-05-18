package com.example.db.port.in;

import com.example.db.character.Wizard;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WizardPort {
    ResponseEntity<List<Wizard>> getWizards();
    ResponseEntity<Wizard> create(Wizard wizard);
    ResponseEntity<Wizard> changeStatusWizard(String id);
}

package com.example.db.service;

import com.example.db.character.Wizard;
import com.example.db.exceptions.NotFoundException;
import com.example.db.port.out.DatabasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WizardService {

    private final DatabasePort databasePort;

    public List<Wizard> getWizards() {
        return databasePort.getWizards();

    }
    public Wizard createWizard(String name) {
        return databasePort.createWizard(name);
    }

    public Wizard changeStatusWizard(String id) {
        Optional<Wizard> wizardOptional = databasePort.changeStatusWizard(id);
        if (wizardOptional.isEmpty()) throw new NotFoundException();
        return wizardOptional.get();
    }
}

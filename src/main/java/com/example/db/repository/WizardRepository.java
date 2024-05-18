package com.example.db.repository;

import com.example.db.character.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends JpaRepository <Wizard, String> {
}

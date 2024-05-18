package com.example.db.character;

import com.example.db.model.WizardHouse;
import com.example.db.model.WizardPet;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wizard")
public class Wizard{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private WizardHouse wizardHouse;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private WizardPet wizardPet;

    @Column
    @Builder.Default
    private boolean active = true;

}

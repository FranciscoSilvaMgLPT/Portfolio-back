package com.example.db.character;

import com.example.db.model.AnimalType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shrek")
public class Shrek {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id", unique = true)
        private String id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        @Enumerated(value = EnumType.STRING)
        private AnimalType animalType;

        @Column
        @Builder.Default
        private boolean active = true;

    }

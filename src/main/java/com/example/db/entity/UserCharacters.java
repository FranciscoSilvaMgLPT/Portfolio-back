package com.example.db.entity;

import com.example.db.character.Shrek;
import com.example.db.character.Wizard;
import com.example.db.repository.UserCharacterRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_characters")
public class UserCharacters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userOwner")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wizard")
    @Nullable
    private Wizard wizard;

    @ManyToOne
    @JoinColumn(name = "shrek")
    @Nullable
    private Shrek shrek;

}

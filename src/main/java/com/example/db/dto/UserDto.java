package com.example.db.dto;

import com.example.db.character.Shrek;
import com.example.db.character.Wizard;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String password;
    @Builder.Default
    private List<Shrek> shrekList = new ArrayList<>();
    @Builder.Default
    private List<Wizard> wizardList = new ArrayList<>();
 /*   private List<String> pokemonList;*/
    @Builder.Default
    private boolean active = true;
}

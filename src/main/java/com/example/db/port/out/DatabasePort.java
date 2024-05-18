package com.example.db.port.out;

import com.example.db.character.Shrek;
import com.example.db.character.Wizard;
import com.example.db.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DatabasePort {

    List<User> getUsers();

    void createUser(User user);

    void patchUser(User user);

    Optional<User> getUserByEmail(String email);

    List<Wizard> getWizards();

    Wizard createWizard(String name);

    Optional<Wizard> changeStatusWizard(String wizard);

    List<Shrek> getShreks();

    Shrek createShrek(String name);

    Shrek changeStatusShrek(String id);
    String getPokemons();

}

package com.example.db;

import com.example.db.character.Shrek;
import com.example.db.character.Wizard;
import com.example.db.entity.User;
import com.example.db.entity.UserCharacters;
import com.example.db.exceptions.NotFoundException;
import com.example.db.exceptions.ParameterNotFoundException;
import com.example.db.model.AnimalType;
import com.example.db.model.WizardHouse;
import com.example.db.model.WizardPet;
import com.example.db.port.out.DatabasePort;
import com.example.db.repository.ShrekRepository;
import com.example.db.repository.UserCharacterRepository;
import com.example.db.repository.UserRepository;
import com.example.db.repository.WizardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@AllArgsConstructor
public class PostgresHandler implements DatabasePort {

    private final UserRepository userRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final WizardRepository wizardRepository;
    private final ShrekRepository shrekRepository;
    private final Random random;


    //   >>>>>>>USER<<<<
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void patchUser(User user) {
        Optional<User> optUser = userRepository.findById(user.getId());
        if (optUser.isEmpty()) throw new NotFoundException();

        User userToSave = optUser.get();

        if (user.getEmail() != null && !user.getEmail().isBlank()) userToSave.setEmail(user.getEmail());
        if (user.getName() != null && !user.getName().isBlank()) userToSave.setName(user.getName());
        if (user.getPassword() != null && !user.getPassword().isBlank()) userToSave.setPassword(user.getPassword());

        userToSave.setActive(user.isActive());

        userRepository.save(userToSave);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) throw new NotFoundException();
        return userOptional;
    }

    //   >>>>>>>WIZARDS<<<<
    @Override
    public List<Wizard> getWizards() {
        return wizardRepository.findAll();
    }

    @Transactional
    @Override
    public Wizard createWizard(String name) {
        if (name.isBlank() || name.startsWith(" ")) throw new ParameterNotFoundException("Invalid name.");
        Optional<User> user = userRepository.findById("bfc34c6c-527b-4f47-af77-fb6d71a31252");
        if (user.isEmpty()) throw new NotFoundException();

        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        User userFromRep = user.get();

        int randomHouse = random.nextInt(WizardHouse.values().length);
        int randomPet = random.nextInt(WizardPet.values().length);

        Wizard wizard = wizardRepository.save(Wizard.builder()
                .name(name)
                .wizardHouse(WizardHouse.values()[randomHouse])
                .wizardPet(WizardPet.values()[randomPet])
                .build());

        UserCharacters userCharacters = UserCharacters.builder()
                .user(userFromRep)
                .wizard(wizard)
                .build();

        userCharacterRepository.save(userCharacters);
        userRepository.save(userFromRep);
        return wizard;
    }

    @Override
    public Optional<Wizard> changeStatusWizard(String wizard) {
        return Optional.empty();
    }

    //   >>>>>>>SHREKS<<<<

    @Override
    public List<Shrek> getShreks() {
        return shrekRepository.findAll();
    }


    @Override
    public Shrek createShrek(String name) {
        if (name.isBlank() || name.startsWith(" ")) throw new ParameterNotFoundException("Invalid name.");

        int randomAnimalIndex = random.nextInt(AnimalType.values().length);
        AnimalType randomAnimalType = AnimalType.values()[randomAnimalIndex];

        Optional<User> user = userRepository.findById("bfc34c6c-527b-4f47-af77-fb6d71a31252");

        if (user.isEmpty()) throw new NotFoundException();
        User userFromRep = user.get();

        User user1 = userRepository.save(userFromRep);

        Shrek newShrek = Shrek.builder()
                .name(name)
                .animalType(randomAnimalType)
                .build();

        Shrek savedShrek = shrekRepository.save(newShrek);

        UserCharacters userCharacters = UserCharacters.builder()
                .user(user1)
                .shrek(savedShrek)
                .build();

        userCharacterRepository.save(userCharacters);
        return savedShrek;
    }


    @Override
    public Shrek changeStatusShrek(String id) {
        Optional<Shrek> shrekOpt = shrekRepository.findById(id);
        if(shrekOpt.isEmpty()) throw new NotFoundException();
        Shrek shrekGet = shrekOpt.get();
        shrekGet.setActive(!shrekGet.isActive());

        return shrekGet;
    }

    //   >>>>>>>POKEMONS<<<<

    @Override
    public String getPokemons() {
        return "null";
    }

}

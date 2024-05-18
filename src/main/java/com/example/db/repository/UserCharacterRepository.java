package com.example.db.repository;

import com.example.db.entity.UserCharacters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCharacterRepository extends JpaRepository<UserCharacters, Long> {
}

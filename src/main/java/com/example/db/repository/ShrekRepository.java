package com.example.db.repository;

import com.example.db.character.Shrek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShrekRepository extends JpaRepository <Shrek, String> {
}

package com.example.db.port.in;

import com.example.db.character.Shrek;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShrekPort {
    ResponseEntity<List<Shrek>> getShreks();
    ResponseEntity<Shrek> create(Shrek shrek);
    ResponseEntity<Shrek> changeStatusShrek(String id);
}

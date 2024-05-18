package com.example.db.controller;

import com.example.db.character.Shrek;
import com.example.db.port.in.ShrekPort;
import com.example.db.service.ShrekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shrek")
public class ShrekController implements ShrekPort {

    private final ShrekService shrekService;

    @GetMapping
    @Override
    public ResponseEntity<List<Shrek>> getShreks() {
        return ResponseEntity.ok(shrekService.getShreks());
    }

    @PostMapping
    @Override
    public ResponseEntity<Shrek> create(@RequestBody Shrek shrek) {
       return ResponseEntity.ok(shrekService.createShrek(shrek.getName()));
    }

    @PatchMapping("/{id}/status")
    @Override
    public ResponseEntity<Shrek> changeStatusShrek(@PathVariable String id) {
        return ResponseEntity.ok(shrekService.changeStatusShrek(id));
    }
}

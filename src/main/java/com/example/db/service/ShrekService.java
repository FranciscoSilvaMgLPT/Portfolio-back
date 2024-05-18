package com.example.db.service;

import com.example.db.character.Shrek;
import com.example.db.port.out.DatabasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShrekService {

    private final DatabasePort databasePort;

    public List<Shrek> getShreks(){
        return databasePort.getShreks();
    }

    public Shrek createShrek(String name) {
        return databasePort.createShrek(name);
    }

    public Shrek changeStatusShrek(String id) {
        return databasePort.changeStatusShrek(id);
    }
}

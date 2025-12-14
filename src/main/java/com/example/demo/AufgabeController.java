package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aufgaben")
@CrossOrigin(origins = "*")
public class AufgabeController {

    private final AufgabeRepository repo;

    public AufgabeController(AufgabeRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Aufgabe> getAll() {
        return repo.findAll();
    }


    @PostMapping
    public Aufgabe create(@RequestBody Aufgabe aufgabe) {
        return repo.save(aufgabe);
    }
}

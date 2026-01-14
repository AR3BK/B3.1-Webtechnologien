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

    // GET alle Aufgaben
    @GetMapping
    public List<Aufgabe> getAll() {
        return repo.findAll();
    }

    // POST neue Aufgabe
    @PostMapping
    public Aufgabe create(@RequestBody Aufgabe aufgabe) {
        return repo.save(aufgabe);
    }

    // PUT Aufgabe bearbeiten
    @PutMapping("/{id}")
    public Aufgabe update(
            @PathVariable Long id,
            @RequestBody Aufgabe updated
    ) {
        Aufgabe a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aufgabe nicht gefunden"));

        a.setTitel(updated.getTitel());
        a.setBeschreibung(updated.getBeschreibung());

        return repo.save(a);
    }

    // PUT Status toggle
    @PutMapping("/{id}/toggle")
    public Aufgabe toggle(@PathVariable Long id) {
        Aufgabe a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aufgabe nicht gefunden"));

        a.setErledigt(!a.isErledigt());
        return repo.save(a);
    }

    // DELETE Aufgabe
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

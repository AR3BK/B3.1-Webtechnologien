package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AufgabeController {

    @GetMapping("/aufgaben")
    public List<Aufgabe> getAufgaben() {
        return List.of(
                new Aufgabe("Hausaufgabe", "Mathe machen", false),
                new Aufgabe("Einkaufen", "Milch und Brot", true)
        );
    }
}

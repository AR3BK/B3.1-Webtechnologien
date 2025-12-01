package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin( origins = {"http://localhost:3000", "https://b3-1-webtechnologien-1.onrender.com" } )
public class AufgabeController {

    @GetMapping("/aufgaben")
    public List<Aufgabe> getAufgaben() {
        return List.of(
                new Aufgabe("Hausaufgabe", "Mathe machen", false),
                new Aufgabe("Einkaufen", "Milch und Brot", true)
        );
    }
}

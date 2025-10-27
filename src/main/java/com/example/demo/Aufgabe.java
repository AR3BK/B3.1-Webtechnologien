package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aufgabe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titel;
    private String beschreibung;
    private boolean erledigt;

    public Aufgabe() {}

    public Aufgabe(String titel, String beschreibung, boolean erledigt) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.erledigt = erledigt;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }
    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
    public boolean isErledigt() { return erledigt; }
    public void setErledigt(boolean erledigt) { this.erledigt = erledigt; }
}

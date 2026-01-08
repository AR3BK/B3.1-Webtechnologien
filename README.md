# B3.1 Webtechnologien – Todo App

Diese Anwendung ist eine einfache Todo-Web-App im Rahmen der Veranstaltung  
**B3.1 Webtechnologien**.

Ziel des Projekts ist es, eine vollständige Web-Anwendung mit Frontend, Backend,
Datenbank und automatisierten Tests umzusetzen.

---

## Projektübersicht

Die App ermöglicht es, Aufgaben zu erstellen, zu bearbeiten, abzuschließen und zu löschen.
Offene und erledigte Aufgaben werden getrennt dargestellt.

Das Frontend wurde mit **Vue.js** umgesetzt und kommuniziert über eine
**Spring Boot REST API** mit dem Backend.  
Die Daten werden in einer **PostgreSQL-Datenbank** gespeichert, die ebenfalls auf Render läuft.

---

## Funktionen

- Aufgaben erstellen (Titel, optionale Beschreibung)
- Aufgaben bearbeiten
- Aufgaben als erledigt / offen markieren
- Aufgaben löschen
- Getrennte Ansicht für offene und erledigte Aufgaben
- Persistente Speicherung in der Datenbank

---

## Technischer Stack

- Frontend: Vue.js (Vite)
- Backend: Spring Boot (REST API)
- Datenbank: PostgreSQL
- Deployment: Render
- Tests:
    - Backend: JUnit (Spring Boot Tests)
    - Frontend: Vitest
- CI/CD: GitHub Actions

---

## Tests & Continuous Integration

Für das Projekt sind automatisierte Tests eingerichtet:

- Backend-Tests prüfen die REST-API
- Frontend-Tests prüfen Vue-Komponenten
- Die Tests werden automatisch über **GitHub Actions** bei jedem Push und Pull Request ausgeführt

---

## Deployment

- Frontend, Backend und Datenbank sind auf **Render** deployed
- Die Anwendung ist öffentlich erreichbar

---

## Nutzung

1. Anwendung im Browser öffnen
2. Neue Aufgaben über „Neue Aufgabe hinzufügen“ erstellen
3. Aufgaben bearbeiten, erledigen oder löschen
4. Erledigte Aufgaben werden im entsprechenden Tab angezeigt

---

## Hinweise

- Es werden keine Zugangsdaten im Repository gespeichert
- Das Repository ist öffentlich, enthält aber keine sensiblen Informationen
- Das Projekt dient Lern- und Übungszwecken im Rahmen der Lehrveranstaltung

package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AufgabeController.class)
class AufgabeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AufgabeRepository aufgabeRepository;

    @Test
    void shouldGetAllAufgaben() throws Exception {
        Aufgabe a = new Aufgabe("Test", "Beschreibung", false);

        when(aufgabeRepository.findAll())
                .thenReturn(List.of(a));

        mockMvc.perform(get("/aufgaben"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titel").value("Test"));
    }

    @Test
    void shouldCreateAufgabe() throws Exception {
        String json = """
                {
                  "titel": "Neue Aufgabe",
                  "beschreibung": "Test Beschreibung"
                }
                """;

        Aufgabe gespeicherteAufgabe =
                new Aufgabe("Neue Aufgabe", "Test Beschreibung", false);

        when(aufgabeRepository.save(org.mockito.ArgumentMatchers.any()))
                .thenReturn(gespeicherteAufgabe);

        mockMvc.perform(post("/aufgaben")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titel").value("Neue Aufgabe"));
    }
}

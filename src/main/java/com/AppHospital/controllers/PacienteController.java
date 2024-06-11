package com.AppHospital.controllers;

import com.AppHospital.models.HistoricoChamada;
import com.AppHospital.models.Paciente;
import com.AppHospital.repositories.PacienteRepository;
import com.AppHospital.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity.ok().body(pacienteService.listarPacientesPorPrioridade());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> getPaciente(@PathVariable Long id) {
        return ResponseEntity.ok().body(pacienteRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> postPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok().body(pacienteService.postPaciente(paciente));
    }

    @PostMapping("/chamar")
    public ResponseEntity<Void> chamarPaciente() {
        pacienteService.chamarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/historico")
    public ResponseEntity<List<HistoricoChamada>> listarHistoricoChamadas() {
        return ResponseEntity.ok().body(pacienteService.listarHistoricoChamadas());
    }
}

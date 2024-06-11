package com.AppHospital.services;

import com.AppHospital.models.HistoricoChamada;
import com.AppHospital.models.Paciente;
import com.AppHospital.repositories.HistoricoChamadaRepository;
import com.AppHospital.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HistoricoChamadaRepository historicoChamadaRepository;

    public Paciente postPaciente(Paciente paciente) {
        paciente.setStatus("Aguardando");
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientesPorPrioridade() {
        return pacienteRepository.findAllByOrderByPrioridadeAsc();
    }

    public void chamarPaciente() {
        List<Paciente> pacientes = listarPacientesPorPrioridade();
        for (Paciente paciente : pacientes) {
            if ("Aguardando".equals(paciente.getStatus())) {
                paciente.setStatus("Atendido");
                pacienteRepository.save(paciente);

                HistoricoChamada historico = new HistoricoChamada();
                historico.setPaciente(paciente);
                historico.setHorarioChamada(LocalDateTime.now());
                historicoChamadaRepository.save(historico);
                break;
            }
        }
    }

    public List<HistoricoChamada> listarHistoricoChamadas() {
        return historicoChamadaRepository.findAll();
    }
}

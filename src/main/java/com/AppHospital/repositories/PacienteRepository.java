package com.AppHospital.repositories;

import com.AppHospital.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByOrderByPrioridadeAsc();
}

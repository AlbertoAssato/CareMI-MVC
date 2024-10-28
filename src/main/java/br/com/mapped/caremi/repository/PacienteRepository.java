package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

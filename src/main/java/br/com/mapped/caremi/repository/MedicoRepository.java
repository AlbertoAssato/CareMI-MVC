package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}

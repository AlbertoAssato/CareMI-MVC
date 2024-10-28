package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.PacientePlanoSaude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientePlanoSaudeRepository extends JpaRepository<PacientePlanoSaude, Long> {
}

package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.PlanoSaude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoSaudeRepository extends JpaRepository<PlanoSaude, Long> {
}

package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}

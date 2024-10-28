package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByDataEnvio(LocalDate dataEnvio);
}

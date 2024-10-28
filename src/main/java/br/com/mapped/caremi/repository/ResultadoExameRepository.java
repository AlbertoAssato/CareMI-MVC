package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.ResultadoExame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoExameRepository extends JpaRepository<ResultadoExame, Long> {
}

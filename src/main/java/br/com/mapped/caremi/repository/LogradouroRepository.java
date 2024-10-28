package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
}

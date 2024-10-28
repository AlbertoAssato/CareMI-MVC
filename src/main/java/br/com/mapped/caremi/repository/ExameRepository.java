package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {
}

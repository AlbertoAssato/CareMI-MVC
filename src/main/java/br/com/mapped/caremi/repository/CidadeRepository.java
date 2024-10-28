package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}

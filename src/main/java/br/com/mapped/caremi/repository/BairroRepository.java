package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
}

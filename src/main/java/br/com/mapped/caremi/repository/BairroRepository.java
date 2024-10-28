package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByNomeContainingIgnoreCase(String nome);
}

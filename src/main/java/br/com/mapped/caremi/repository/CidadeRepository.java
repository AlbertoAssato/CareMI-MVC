package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Bairro;
import br.com.mapped.caremi.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByNomeContainingIgnoreCase(String nome);
}

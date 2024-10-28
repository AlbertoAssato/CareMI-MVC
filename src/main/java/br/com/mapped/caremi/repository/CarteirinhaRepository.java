package br.com.mapped.caremi.repository;

import br.com.mapped.caremi.model.Carteirinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteirinhaRepository extends JpaRepository<Carteirinha, Long> {
    List<Carteirinha> findByNomePacienteContainingIgnoreCase(String nomePaciente);
}

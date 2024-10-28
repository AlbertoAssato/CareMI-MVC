package br.com.mapped.caremi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_bairro")
@EntityListeners(AuditingEntityListener.class)
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro")
    @SequenceGenerator(name = "bairro", sequenceName = "seq_mi_bairro", allocationSize = 1)
    @Column(name = "cdBairro", length = 7)
    private Long id;

    @Column(name = "nmBairro", length = 100, nullable = false)
    private String nome;

    @Column(name = "nmZonaBairro", length = 100, nullable = false)
    private String zona;

    @Column(name = "nrCep", length = 10, nullable = false)
    private String cep;



}
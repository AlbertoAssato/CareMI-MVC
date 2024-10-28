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
@Table(name="t_logradouro")
@EntityListeners(AuditingEntityListener.class)
public class Logradouro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logradouro")
    @SequenceGenerator(name = "logradouro", sequenceName = "seq_mi_logradouro", allocationSize = 1)
    @Column(name = "cdLogradouro", length = 9)
    private Long id;

    @Column(name = "nmRua", length = 100, nullable = false)
    private String nome;


}
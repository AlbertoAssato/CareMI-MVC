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
@Table(name="t_estado")
@EntityListeners(AuditingEntityListener.class)
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado")
    @SequenceGenerator(name = "estado", sequenceName = "seq_mi_estado", allocationSize = 1)
    @Column(name = "cdEstado", length = 6)
    private Long id;

    @Column(name = "nmEstado", length = 30, nullable = false)
    private String nome;

    @Column(name = "sgEstado", length = 3, nullable = false)
    private String sigla;


}
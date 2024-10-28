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
@Table(name="t_cidade")
@EntityListeners(AuditingEntityListener.class)
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade")
    @SequenceGenerator(name = "cidade", sequenceName = "seq_mi_cidade", allocationSize = 1)
    @Column(name = "cdCidade", length = 6)
    private Long id;

    @Column(name = "nmCidade", length = 100, nullable = false)
    private String nome;

}
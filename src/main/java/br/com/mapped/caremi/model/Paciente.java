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
@Table(name="t_paciente")
@EntityListeners(AuditingEntityListener.class)
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente")
    @SequenceGenerator(name = "paciente", sequenceName = "seq_mi_paciente", allocationSize = 1)
    @Column(name = "cdPaciente", length = 9)
    private Long id;

    @Column(name = "nmPaciente", length = 100, nullable = false)
    private String nome;

    @Column(name = "nrPeso", length = 6, nullable = false)
    private Integer peso;

    @Column(name = "nrAltura", length = 4, nullable = false)
    private Integer altura;

    @Column(name = "nmGrupoSanguineo", length = 6, nullable = false)
    private String grupoSanguineo;

    @Column(name = "flSexoBiologico", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoBiologico sexoBiologico;



}
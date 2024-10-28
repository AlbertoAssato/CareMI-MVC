package br.com.mapped.caremi.model;
import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.Medico;
import br.com.mapped.caremi.model.Paciente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_atendimento")
@EntityListeners(AuditingEntityListener.class)
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atendimento")
    @SequenceGenerator(name = "atendimento", sequenceName = "seq_mi_atendimento", allocationSize = 1)
    @Column(name = "cdAtendimento", length = 9)
    private Long id;

    @Column(name = "dsDescricao", length = 500, nullable = false)
    private String descricao;

    @Column(name = "qtDias", length = 2, nullable = false)
    private Integer dias;

    @Column(name = "dsHabito", length = 100, nullable = false)
    private String habito;

    @Column(name = "dsTempoSono", length = 10, nullable = false)
    private String tempoSono;

    @Column(name = "dsHereditario", length = 50, nullable = false)
    private String hereditario;

    @Column(name = "dtEnvio", nullable = false)
    private LocalDate dataEnvio;

    @Column(name = "fgAtivo", length = 1)
    private Integer ativo;



}
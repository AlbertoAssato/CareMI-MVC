package br.com.mapped.caremi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_carteirinha")
@EntityListeners(AuditingEntityListener.class)
public class Carteirinha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carteirinha")
    @SequenceGenerator(name = "carteirinha", sequenceName = "seq_mi_carteirinha", allocationSize = 1)
    @Column(name = "cdCarteirinha", length = 9)
    private Long id;

    @Column(name = "nmPaciente", length = 100, nullable = false)
    private String nomePaciente;

    @Column(name = "nmPlanoSaude", length = 100, nullable = false)
    private String planoSaude;

    @Column(name = "nrCns", length = 15, nullable = false)
    private Long cns;

    @Column(name = "nmEmpresa", length = 100, nullable = false)
    private String empresa;

    @Column(name = "dsCarencia", length = 200, nullable = false)
    private String carencia;

    @Column(name = "dsAcomodacao", length = 200, nullable = false)
    private String acomodacao;

    @Column(name = "dtNascimento", nullable = false)
    private LocalDate dataNascimento;



}
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
@Table(name="t_medico")
@EntityListeners(AuditingEntityListener.class)
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico")
    @SequenceGenerator(name = "medico", sequenceName = "seq_mi_medico", allocationSize = 1)
    @Column(name = "cdMedico", length = 9)
    private Long id;

    @Column(name = "nmMedico", length = 100, nullable = false)
    private String nome;

    @Column(name = "dsEspecializacao", length = 200, nullable = false)
    private String especializacao;

    @Column(name = "dsCrm", length = 15, nullable = false)
    private String crm;

    @Column(name = "dsEmail", length = 100, nullable = false)
    private String email;

    @Column(name = "nrCelular", length = 15, nullable = false)
    private String celular;


}
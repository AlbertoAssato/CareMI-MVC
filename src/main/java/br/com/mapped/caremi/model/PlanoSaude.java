package br.com.mapped.caremi.model;

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
@Table(name="t_plano_saude")
@EntityListeners(AuditingEntityListener.class)
public class PlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planoSaude")
    @SequenceGenerator(name = "planoSaude", sequenceName = "seq_mi_pl_saude", allocationSize = 1)
    @Column(name = "cdPlanoSaude", length = 9)
    private Long id;

    @Column(name = "dsRazaoSocial", length = 100, nullable = false)
    private String razaoSocial;

    @Column(name = "nmFantasia", length = 100, nullable = false)
    private String fantasia;

    @Column(name = "nrCnpj", length = 14, nullable = false)
    private Long cnpj;

    @Column(name = "nmContato", length = 100, nullable = false)
    private String contato;

    @Column(name = "nrTelefone", length = 15, nullable = false)
    private String telefone;

    @Column(name = "dtCadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "fgAtivo", length = 1, nullable = false)
    private Integer ativo;



}
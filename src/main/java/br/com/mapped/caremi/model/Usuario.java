package br.com.mapped.caremi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "seq_mi_usuario", allocationSize = 1)
    @Column(name = "cdUsuario", length = 9)
    private Long id;

    @Column(name = "nmUsuario", length = 20, nullable = false)
    private String nome;

    @Column(name = "dtNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nrCpf", length = 15, nullable = false)
    private String cpf;

    @Column(name = "nrRg", length = 15, nullable = false)
    private String rg;

    @Column(name = "dsNacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @Column(name = "dtCadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "dsEstadoCivil", length = 100)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Column(name = "dsProfissao", length = 100)
    private String profissao;

    @Column(name = "dsSenha", length = 100, nullable = false)
    private String senha;

    @Column(name = "fgAtivo", length = 1, nullable = false)
    private Integer ativo;



}
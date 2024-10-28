package br.com.mapped.caremi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_endereco_hospital")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoHospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecoHospital")
    @SequenceGenerator(name = "enderecoHospital", sequenceName = "seq_mi_endereco_hospital", allocationSize = 1)
    @Column(name = "cdEndereco", length = 9)
    private Long id;

    @Column(name = "nrLogradouro", length = 7, nullable = false)
    private Integer numLogradouro;

    @Column(name = "dsPontoReferencia", length = 100, nullable = false)
    private String pontoReferencia;

    @Column(name = "dsComplemento", length = 100, nullable = false)
    private String complemento;



}
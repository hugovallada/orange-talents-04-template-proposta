package com.br.zupacademy.hugo.proposta.cartao.biometria;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numeroCartao;

    @CreationTimestamp
    private LocalDateTime dataDeAssociacao;

    @NotBlank
    private String fingerPrint;

    public Biometria(String numeroCartao, String fingerPrint) {
        this.numeroCartao = numeroCartao;
        this.fingerPrint = Base64.getEncoder().encodeToString(fingerPrint.getBytes());
    }

    /**
     * @deprecated Construtor de uso exclusivo da JPA
     */
    @Deprecated
    public Biometria() {
    }

    public Long getId() {
        return id;
    }
}

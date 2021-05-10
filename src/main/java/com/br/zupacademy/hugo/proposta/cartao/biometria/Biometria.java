package com.br.zupacademy.hugo.proposta.cartao.biometria;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime dataDeAssociacao;

    @NotBlank
    private String fingerPrint;

    @ManyToOne
    @NotNull
    private Cartao cartao;

    public Biometria(Cartao cartao, String fingerPrint) {
        this.cartao = cartao;
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

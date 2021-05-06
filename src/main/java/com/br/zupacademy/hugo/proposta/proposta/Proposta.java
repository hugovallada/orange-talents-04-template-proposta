package com.br.zupacademy.hugo.proposta.proposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documento;

    private String email;

    private String nome;

    private String endereco;

    private BigDecimal salario;

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    /**
     * @deprecated Uso exclusivo da JPA
     */
    @Deprecated
    public Proposta() {
    }

    public Long getId() {
        return id;
    }
}

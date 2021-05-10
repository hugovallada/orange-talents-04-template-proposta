package com.br.zupacademy.hugo.proposta.cartao.renegociacao;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {
    @Id
    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    private LocalDateTime dataCriacao;

    public Renegociacao(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
    }
}

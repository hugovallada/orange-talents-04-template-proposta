package com.br.zupacademy.hugo.proposta.cartao.parcela;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Parcela {
    @Id
    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    @ManyToOne
    private Cartao cartao;

    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcela() {
    }
}

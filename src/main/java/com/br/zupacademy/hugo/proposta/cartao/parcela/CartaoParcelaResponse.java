package com.br.zupacademy.hugo.proposta.cartao.parcela;

import java.math.BigDecimal;

public class CartaoParcelaResponse {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public CartaoParcelaResponse(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Parcela toModel() {
        return new Parcela(id, quantidade, valor);
    }
}

package com.br.zupacademy.hugo.proposta.cartao;

import java.time.LocalDateTime;

public class CartaoVencimentoResponse {
    private String id;

    private Integer dia;

    private LocalDateTime dataDeCriacao;

    public CartaoVencimentoResponse(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}

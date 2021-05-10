package com.br.zupacademy.hugo.proposta.cartao.vencimento;

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

    public Vencimento toModel() {
        return new Vencimento(id, dia, dataDeCriacao);
    }

    @Override
    public String toString() {
        return "CartaoVencimentoResponse{" +
                "id='" + id + '\'' +
                ", dia=" + dia +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}

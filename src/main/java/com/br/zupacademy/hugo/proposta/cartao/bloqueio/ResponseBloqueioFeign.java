package com.br.zupacademy.hugo.proposta.cartao.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBloqueioFeign {

    private String resultado;

    public ResponseBloqueioFeign(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}

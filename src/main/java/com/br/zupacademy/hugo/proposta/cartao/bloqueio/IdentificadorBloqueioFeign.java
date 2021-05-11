package com.br.zupacademy.hugo.proposta.cartao.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdentificadorBloqueioFeign {

    private String sistemaResponsavel;

    public IdentificadorBloqueioFeign(@JsonProperty("sistemaResponsavel") String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}

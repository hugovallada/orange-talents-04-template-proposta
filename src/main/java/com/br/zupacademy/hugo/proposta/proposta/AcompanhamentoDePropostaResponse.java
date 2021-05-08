package com.br.zupacademy.hugo.proposta.proposta;

import java.math.BigDecimal;

public class AcompanhamentoDePropostaResponse {

    private Long id;

    private String nome;

    private Situacao situacao;

    private String documento;


    public AcompanhamentoDePropostaResponse(Proposta proposta){
        this.id = proposta.getId();
        this.nome = proposta.getNome();
        this.situacao = proposta.getSituacao();
        this.documento = proposta.getDocumento();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public String getDocumento() {
        return documento;
    }
}

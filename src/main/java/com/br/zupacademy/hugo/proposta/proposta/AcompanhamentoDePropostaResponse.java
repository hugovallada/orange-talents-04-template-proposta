package com.br.zupacademy.hugo.proposta.proposta;

import java.math.BigDecimal;

public class AcompanhamentoDePropostaResponse {

    private Long id;

    private String documento;

    private String nome;

    private String email;

    private String endereco;

    private BigDecimal salario;

    private Situacao situacao;

    private String numeroCartao;

    public AcompanhamentoDePropostaResponse(Proposta proposta){
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.situacao = proposta.getSituacao();
        this.numeroCartao = proposta.getNumeroCartao();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}

package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.proposta.consulta.ResultadoSolicitacao;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

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

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void atualizarSituacao(ResultadoSolicitacao solicitacao){
        if(solicitacao == ResultadoSolicitacao.SEM_RESTRICAO){
            this.situacao = Situacao.ELEGIVEL;
        } else if(solicitacao == ResultadoSolicitacao.COM_RESTRICAO){
            this.situacao = Situacao.NAO_ELEGIVEL;
        }
    }
}

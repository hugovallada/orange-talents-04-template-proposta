package com.br.zupacademy.hugo.proposta.proposta.consulta;

import com.br.zupacademy.hugo.proposta.proposta.Proposta;
import com.br.zupacademy.hugo.proposta.util.validator.Documento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ConsultaPropostaRequest {

    private @NotNull Long idProposta;

    private @NotBlank String nome;

    private @NotBlank @Documento String documento;

    public ConsultaPropostaRequest(Proposta proposta){
        this.idProposta = proposta.getId();
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
    }
}

package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.util.validator.Documento;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    private @Documento String documento;

    private @Email @NotBlank String email;

    private @NotBlank String nome;

    private @NotBlank String endereco;

    private @NotNull @Positive BigDecimal salario;

    public NovaPropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(){
        return new Proposta(documento, email, nome, endereco, salario);
    }
}

package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.util.encriptor.EncriptorConverter;
import com.br.zupacademy.hugo.proposta.util.validator.Documento;
import com.br.zupacademy.hugo.proposta.util.validator.UniqueValue;
import com.sun.istack.NotNull;

import javax.persistence.Convert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @UniqueValue(targetClass = Proposta.class, campo = "documento")
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

    public String getDocumento() {
        return documento;
    }
}

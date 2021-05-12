package com.br.zupacademy.hugo.proposta.cartao.carteira;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCarteiraRequest {

    private @NotBlank @Email String email;

    private @NotNull TipoDeCarteira emissor;

    public NovaCarteiraRequest(String email, TipoDeCarteira emissor) {
        this.email = email;
        this.emissor = emissor;
    }

    public Carteira toModel(String id, Cartao cartao){
        return new Carteira(id,email, emissor, cartao);
    }

    public String getEmail() {
        return email;
    }

    public TipoDeCarteira getEmissor() {
        return emissor;
    }
}

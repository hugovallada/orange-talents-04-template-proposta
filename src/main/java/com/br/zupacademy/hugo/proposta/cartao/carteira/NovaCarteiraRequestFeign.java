package com.br.zupacademy.hugo.proposta.cartao.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovaCarteiraRequestFeign {
    private @NotBlank @Email String email;

    private @NotBlank String carteira;

    public NovaCarteiraRequestFeign(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}

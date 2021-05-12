package com.br.zupacademy.hugo.proposta.cartao.carteira;

public class NovaCarteiraRequestFeign {
    private String email;

    private String carteira;

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

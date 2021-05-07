package com.br.zupacademy.hugo.proposta.cartao.carteira;

import java.time.LocalDateTime;

public class CartaoCarteiraDigitalResponse {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public CartaoCarteiraDigitalResponse(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}

package com.br.zupacademy.hugo.proposta.cartao.aviso;

import java.time.LocalDate;

public class CartaoAvisoResponse {
    private LocalDate validoAte;
    private String destino;

    public CartaoAvisoResponse(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Aviso toModel() {
        return new Aviso(validoAte, destino);
    }
}

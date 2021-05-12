package com.br.zupacademy.hugo.proposta.cartao.carteira;

public class CarteiraResponseFeign {

    private String id;

    private String resultado;

    public CarteiraResponseFeign(String id, String resultado) {
        this.id = id;
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public String getResultado() {
        return resultado;
    }
}

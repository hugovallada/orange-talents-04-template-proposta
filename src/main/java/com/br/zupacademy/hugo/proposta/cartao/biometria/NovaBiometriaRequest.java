package com.br.zupacademy.hugo.proposta.cartao.biometria;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

    @NotBlank
    private String fingerPrintLimpa;

    public NovaBiometriaRequest(@JsonProperty("fingerPrintLimpa") String fingerPrintLimpa) {
        this.fingerPrintLimpa = fingerPrintLimpa;
    }

    public Biometria toModel(String numeroCartao){
        return new Biometria(numeroCartao, fingerPrintLimpa);
    }
}

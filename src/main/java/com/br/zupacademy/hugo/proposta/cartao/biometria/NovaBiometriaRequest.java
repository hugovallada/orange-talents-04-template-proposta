package com.br.zupacademy.hugo.proposta.cartao.biometria;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class NovaBiometriaRequest {

    @NotBlank
    private String fingerPrint;

    public NovaBiometriaRequest(@JsonProperty("fingerPrint") String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Biometria toModel(Cartao cartao){
        System.out.println(fingerPrint);
        return new Biometria(cartao, Base64.getDecoder().decode(fingerPrint.getBytes(StandardCharsets.UTF_8)));
    }
}

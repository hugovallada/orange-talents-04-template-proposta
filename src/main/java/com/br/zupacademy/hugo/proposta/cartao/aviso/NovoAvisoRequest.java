package com.br.zupacademy.hugo.proposta.cartao.aviso;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoAvisoRequest {

    private @NotBlank String destino;

    private @Future @NotNull LocalDate validoAte;

    public NovoAvisoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public Aviso toModel(Cartao cartao, String userAgent, String ipClient) {
        return new Aviso(validoAte,destino,ipClient, userAgent,cartao);
    }
}

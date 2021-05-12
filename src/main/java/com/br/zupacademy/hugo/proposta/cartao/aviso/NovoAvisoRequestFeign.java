package com.br.zupacademy.hugo.proposta.cartao.aviso;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoAvisoRequestFeign {
    private @NotBlank String destino;

    private @Future @NotNull LocalDate validoAte;

    public NovoAvisoRequestFeign(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public NovoAvisoRequestFeign(NovoAvisoRequest avisoRequest){
        this.destino = avisoRequest.getDestino();
        this.validoAte = avisoRequest.getValidoAte();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}

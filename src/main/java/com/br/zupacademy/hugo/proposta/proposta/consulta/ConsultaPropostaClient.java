package com.br.zupacademy.hugo.proposta.proposta.consulta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ConsultaProposta", url ="http://localhost:9999/" )
public interface ConsultaPropostaClient {

    @PostMapping("api/solicitacao")
    public ConsultaPropostaResponse solicitacao(ConsultaPropostaRequest consultaPropostaRequest);
}

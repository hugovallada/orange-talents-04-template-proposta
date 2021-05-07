package com.br.zupacademy.hugo.proposta.proposta.consulta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ConsultaProposta", url ="http://localhost:9999/api/solicitacao" )
public interface ConsultaPropostaClient {

    @GetMapping
    public ConsultaPropostaResponse solicitacao(ConsultaPropostaRequest consultaPropostaRequest);
}

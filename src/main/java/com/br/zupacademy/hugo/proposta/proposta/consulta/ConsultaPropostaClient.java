package com.br.zupacademy.hugo.proposta.proposta.consulta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "consultaProposta", url = "${consulta.solicitacao.url}")
public interface ConsultaPropostaClient {

    @PostMapping("/api/solicitacao")
    ConsultaPropostaResponse solicitacao(ConsultaPropostaRequest consultaPropostaRequest);
}

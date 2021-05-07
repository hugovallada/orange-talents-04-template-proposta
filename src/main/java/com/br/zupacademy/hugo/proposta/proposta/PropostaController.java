package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.cartao.CartaoClient;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaClient;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaRequest;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaResponse;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ResultadoSolicitacao;
import com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil;
import com.br.zupacademy.hugo.proposta.util.transaction.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil.ofuscarDados;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaPropostaClient consultaPropostaClient;

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private ExecutorTransacao executorTransacao;

    private Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PostMapping
    public ResponseEntity<Void> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                                    UriComponentsBuilder uriComponentsBuilder){

        if(propostaRepository.existsByDocumento(novaPropostaRequest.getDocumento())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Um problema aconteceu!Verifique o seu documento");
        }

        Proposta proposta = executorTransacao.salvaEComita(novaPropostaRequest.toModel(), propostaRepository);

        atualizarStatusProposta(proposta);

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }

    private void atualizarStatusProposta(Proposta proposta) {
        ConsultaPropostaRequest consulta = new ConsultaPropostaRequest(proposta);

        try{
            ConsultaPropostaResponse consultaResponse = consultaPropostaClient.solicitacao(consulta);
            proposta.atualizarSituacao(consultaResponse.getResultadoSolicitacao());
            executorTransacao.salvaEComita(proposta, propostaRepository);
            logger.info("Proposta de id " + consulta.getIdProposta() + " com documento " + ofuscarDados(consulta.getDocumento()) + " está elegível");
        } catch (FeignException.UnprocessableEntity exception){
            proposta.atualizarSituacao(ResultadoSolicitacao.COM_RESTRICAO);
            executorTransacao.salvaEComita(proposta, propostaRepository);
            logger.info("Proposta de id " + consulta.getIdProposta() + " com documento  " + ofuscarDados(consulta.getDocumento())  + " não está elegível");
        }
    }

}

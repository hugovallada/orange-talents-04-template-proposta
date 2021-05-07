package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaClient;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaRequest;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaResponse;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ResultadoSolicitacao;
import com.br.zupacademy.hugo.proposta.util.transaction.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaPropostaClient consultaPropostaClient;

    @Autowired
    private ExecutorTransacao executorTransacao;

    private Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PostMapping
    public ResponseEntity<Void> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                                    UriComponentsBuilder uriComponentsBuilder){

        if(propostaRepository.existsByDocumento(novaPropostaRequest.getDocumento())){
            //TODO: Deveria criar uma ApiExceptionHandler genérica que faz o mesmo que a ResponseStatusException ?
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Um problema aconteceu!Verifique o seu documento");
        }

        Proposta proposta = executorTransacao.salvaEComita(novaPropostaRequest.toModel(), propostaRepository);

        ConsultaPropostaRequest consulta = new ConsultaPropostaRequest(proposta);

        try{
            ConsultaPropostaResponse consultaResponse = consultaPropostaClient.solicitacao(consulta);

            proposta.atualizarSituacao(consultaResponse.getResultadoSolicitacao());

            executorTransacao.salvaEComita(proposta, propostaRepository);
        } catch (FeignException.UnprocessableEntity exception){
            logger.info("Proposta de id " + consulta.getIdProposta() + " com documento terminado em " + consulta.getDocumento().substring(consulta.getDocumento().length() -3) + " não está elegível");
            proposta.atualizarSituacao(ResultadoSolicitacao.COM_RESTRICAO);
            executorTransacao.salvaEComita(proposta, propostaRepository);
        }

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }

}

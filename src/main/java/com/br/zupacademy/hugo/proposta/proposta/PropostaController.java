package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaClient;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaRequest;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaPropostaClient consultaPropostaClient;


    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                                    UriComponentsBuilder uriComponentsBuilder){

        if(propostaRepository.existsByDocumento(novaPropostaRequest.getDocumento())){
            //TODO: Deveria criar uma ApiExceptionHandler genérica que faz o mesmo que a ResponseStatusException ?
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Um problema aconteceu!Verifique o seu documento");
        }

        Proposta proposta = propostaRepository.save(novaPropostaRequest.toModel());

        ConsultaPropostaRequest consulta = new ConsultaPropostaRequest(proposta);

        ConsultaPropostaResponse consultaResponse = consultaPropostaClient.solicitacao(consulta);

        proposta.atualizarSituacao(consultaResponse.getResultadoSolicitacao());

        propostaRepository.save(proposta);

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }

}

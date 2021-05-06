package com.br.zupacademy.hugo.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;


    @PostMapping
    public ResponseEntity<Void> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                                    UriComponentsBuilder uriComponentsBuilder){
        Proposta proposta = propostaRepository.save(novaPropostaRequest.toModel());
        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }

}

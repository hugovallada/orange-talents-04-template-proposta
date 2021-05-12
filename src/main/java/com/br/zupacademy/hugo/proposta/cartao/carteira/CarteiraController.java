package com.br.zupacademy.hugo.proposta.cartao.carteira;

import com.br.zupacademy.hugo.proposta.cartao.CartaoClient;
import com.br.zupacademy.hugo.proposta.cartao.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.rmi.UnexpectedException;

@RestController
@RequestMapping("/cartoes")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{idCartao}/carteiras")
    public ResponseEntity<Void> associarCarteira(@PathVariable String idCartao, @RequestBody @Valid NovaCarteiraRequest carteiraRequest, UriComponentsBuilder uriComponentsBuilder){
        var cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"O cartão não foi encontrado"));

        // Verifica se o cartão já está associado a esse emissor de carteira
        if(carteiraRepository.findByEmissorAndCartaoId(carteiraRequest.getEmissor(), idCartao).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Esse cartão já está associado a uma carteira desse tipo");
        }


        try{

            var response = cartaoClient.associarCarteira(idCartao, new NovaCarteiraRequestFeign(carteiraRequest.getEmail(),carteiraRequest.getEmissor().toString()));

            if(response.getResultado().equals("ASSOCIADA")){
                var carteira = carteiraRequest.toModel(response.getId(), cartao);
                carteiraRepository.save(carteira);

                var uri = uriComponentsBuilder.path("/cartoes/carteiras/{idCarteira}")
                        .buildAndExpand(carteira.getId()).toUri();

                return ResponseEntity.created(uri).build();
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O sistema externo retornou uma resposta inesperada");

        }catch (FeignException exception){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível associar o cartão");
        }

    }


}

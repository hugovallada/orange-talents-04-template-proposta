package com.br.zupacademy.hugo.proposta.cartao.biometria;

import com.br.zupacademy.hugo.proposta.cartao.CartaoClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("cadastrar/{numeroCartao}")
    public ResponseEntity<Void> cadastrarBiometria(
            @RequestBody @Valid NovaBiometriaRequest biometriaRequest,
            @PathVariable String numeroCartao,
            UriComponentsBuilder componentsBuilder
            ){

        try{
            var cartao = cartaoClient.consultaDadosDoCartao(numeroCartao);
            var biometria = biometriaRepository.save(biometriaRequest.toModel(numeroCartao));

            var uri = componentsBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId().toString()).toUri();

            return ResponseEntity.created(uri).build();

        }catch (FeignException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }

    }
}

package com.br.zupacademy.hugo.proposta.cartao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @PostMapping("/bloquear")
    public ResponseEntity<Void> bloquearCartao(@RequestHeader("user-agent") String userAgent, HttpServletRequest request){
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getHeader("user-agent"));
        System.out.println(userAgent);
        return ResponseEntity.noContent().build();
    }
}

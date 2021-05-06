package com.br.zupacademy.hugo.proposta.util.error;

public class ApiErrorResponse {

    private String onde;

    private String mensagem;

    public ApiErrorResponse(String onde, String mensagem) {
        this.onde = onde;
        this.mensagem = mensagem;
    }

    public String getOnde() {
        return onde;
    }

    public String getMensagem() {
        return mensagem;
    }
}

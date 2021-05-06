package com.br.zupacademy.hugo.proposta.util.error;

import java.util.List;

public class ApiErrorResponse {


    private List<String> mensagens;

    public ApiErrorResponse(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}

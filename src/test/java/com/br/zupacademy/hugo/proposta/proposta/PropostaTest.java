package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.proposta.consulta.ResultadoSolicitacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PropostaTest {


    private Proposta proposta;

    @BeforeEach
    void setUp() {
        proposta = new Proposta("347.861.860-66","email@email.com","Teste","Rua do Teste", new BigDecimal(300.00));
    }

    @Test
    void deveSerNãoElegivelQuandoOResultadoSolicitacaoFoiComRestricao(){
        proposta.atualizarSituacao(ResultadoSolicitacao.COM_RESTRICAO);

        Assertions.assertEquals(proposta.getSituacao(), Situacao.NAO_ELEGIVEL);
    }

    @Test
    void deveSerElegivelQuandoOResultadoSolicitacaoForSemRestricao(){
        proposta.atualizarSituacao(ResultadoSolicitacao.SEM_RESTRICAO);

        Assertions.assertEquals(proposta.getSituacao(), Situacao.ELEGIVEL);
    }
}

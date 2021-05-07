package com.br.zupacademy.hugo.proposta.util;

import com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil.ofuscarDados;

public class LoggerUtilTest {

    @ParameterizedTest
    @CsvSource({
            "43390188088,4*******088",
            "hugo@mail.com,h***@****.com",
            "josevalinhos@hotmail.com, j***********@*******.com",
            "5420 9832 2312 7283, 5*** **** **** *283"
    })
    void dadosDevemSerOfuscadosNoPadraoCorreto(String entrada, String saida){
        Assertions.assertEquals(saida, ofuscarDados(entrada));
    }
}

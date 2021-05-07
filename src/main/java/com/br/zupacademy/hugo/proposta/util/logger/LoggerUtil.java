package com.br.zupacademy.hugo.proposta.util.logger;

public class LoggerUtil {

    public static String ofuscarDados(String dadoASerOfuscado){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< dadoASerOfuscado.length(); i++){
            char val = !(i == 0 || i >= dadoASerOfuscado.length() -3 ||verificacaoDeChars(dadoASerOfuscado.charAt(i))) ? '*' : dadoASerOfuscado.charAt(i);
            sb.append(val);
        }

        return sb.toString();
    }

    private static boolean verificacaoDeChars(Character character){
        return (character == '@' || character == '.' || character == '*');
    }
}

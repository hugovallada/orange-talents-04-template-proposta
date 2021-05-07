package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.cartao.aviso.CartaoAvisoResponse;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.CartaoBloqueioResponse;
import com.br.zupacademy.hugo.proposta.cartao.carteira.CartaoCarteiraDigitalResponse;
import com.br.zupacademy.hugo.proposta.cartao.parcela.CartaoParcelaResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartaoSituacaoResponse {

    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    private List<CartaoBloqueioResponse> bloqueios = new ArrayList<>();

    private List<CartaoAvisoResponse> avisos = new ArrayList<>();

    private List<CartaoCarteiraDigitalResponse> carteiras = new ArrayList<>();

    private List<CartaoParcelaResponse> parcelas = new ArrayList<>();

    private BigDecimal limite;

    private String renegociacao;

    private CartaoVencimentoResponse vencimento;

    private Long idProposta;

    public CartaoSituacaoResponse(String id, LocalDateTime emitidoEm, String titular, List<CartaoBloqueioResponse> bloqueios, List<CartaoAvisoResponse> avisos, List<CartaoCarteiraDigitalResponse> carteiras, List<CartaoParcelaResponse> parcelas, BigDecimal limite, String renegociacao, CartaoVencimentoResponse vencimento, Long idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }
}

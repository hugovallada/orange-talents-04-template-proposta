package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.cartao.aviso.CartaoAvisoResponse;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.CartaoBloqueioResponse;
import com.br.zupacademy.hugo.proposta.cartao.carteira.CartaoCarteiraDigitalResponse;
import com.br.zupacademy.hugo.proposta.cartao.parcela.CartaoParcelaResponse;
import com.br.zupacademy.hugo.proposta.cartao.renegociacao.Renegociacao;
import com.br.zupacademy.hugo.proposta.cartao.vencimento.CartaoVencimentoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartaoSituacaoResponse {

    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    private List<CartaoBloqueioResponse> bloqueios = new ArrayList<>();

    private List<CartaoAvisoResponse> avisos = new ArrayList<>();

    private List<CartaoCarteiraDigitalResponse> carteiras = new ArrayList<>();

    private List<CartaoParcelaResponse> parcelas = new ArrayList<>();

    private BigDecimal limite;

    private Renegociacao renegociacao;

    private CartaoVencimentoResponse vencimento;

    private Long idProposta;

    public CartaoSituacaoResponse(String id, LocalDateTime emitidoEm, String titular, List<CartaoBloqueioResponse> bloqueios, List<CartaoAvisoResponse> avisos, List<CartaoCarteiraDigitalResponse> carteiras, List<CartaoParcelaResponse> parcelas, BigDecimal limite, Renegociacao renegociacao, CartaoVencimentoResponse vencimento, Long idProposta) {
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

    public Cartao toModel(){
        return new Cartao(
                id,
                emitidoEm,
                titular,
                bloqueios.stream().map(CartaoBloqueioResponse::toModel).collect(Collectors.toList()),
                avisos.stream().map(CartaoAvisoResponse::toModel).collect(Collectors.toList()),
                carteiras.stream().map(CartaoCarteiraDigitalResponse::toModel).collect(Collectors.toList()),
                parcelas.stream().map(CartaoParcelaResponse::toModel).collect(Collectors.toList()),
                limite,
                renegociacao,
                vencimento.toModel(),
                idProposta
        );
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CartaoSituacaoResponse{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", limite=" + limite +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                ", idProposta=" + idProposta +
                '}';
    }
}

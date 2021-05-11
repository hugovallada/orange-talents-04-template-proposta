package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.cartao.aviso.Aviso;
import com.br.zupacademy.hugo.proposta.cartao.biometria.Biometria;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.StatusCartao;
import com.br.zupacademy.hugo.proposta.cartao.carteira.Carteira;
import com.br.zupacademy.hugo.proposta.cartao.parcela.Parcela;
import com.br.zupacademy.hugo.proposta.cartao.renegociacao.Renegociacao;
import com.br.zupacademy.hugo.proposta.cartao.vencimento.Vencimento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Aviso> avisos = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Carteira> carteiras = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Parcela> parcelas = new ArrayList<>();

    private BigDecimal limite;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Renegociacao renegociacao;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Vencimento vencimento;

    @OneToMany(mappedBy = "cartao")
    private Set<Biometria> biometrias = new HashSet<>();

    private Long idProposta;

    @Enumerated(EnumType.STRING)
    private StatusCartao estado = StatusCartao.ATIVO;

    public Cartao(String id, LocalDateTime emitidoEm, String titular, List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas, BigDecimal limite, Renegociacao renegociacao, Vencimento vencimento, Long idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Cartao() {
    }

    public boolean estaBloqueado() {
        return estado == StatusCartao.BLOQUEADO;
    }

    public String getId() {
        return id;
    }

    public void bloquearCartao() {
        this.estado = StatusCartao.BLOQUEADO;
    }
}

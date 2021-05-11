package com.br.zupacademy.hugo.proposta.cartao.bloqueio;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private String ipCliente;

    private String userAgent;

    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    public Bloqueio(Long id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(Cartao cartao, String ipCliente, String userAgent) {
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.ativo = true;
        this.sistemaResponsavel = "Sistema de Proposta";
    }


    /**
     * @deprecated Uso exclusivo da Jpa
     */
    @Deprecated
    public Bloqueio() {
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}

package com.br.zupacademy.hugo.proposta.cartao.aviso;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.DependsOn;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validoAte;

    @CreationTimestamp
    private LocalDateTime instanteDeRegistro;

    private String destino;

    private String ipCliente;

    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    public Aviso(LocalDate validoAte, String destino, String ipCliente, String userAgent, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    /**
     * @deprecated Construtor de uso exclusivo do Jpa
     */
    @Deprecated
    public Aviso() {
    }
}

package com.gerenciadordedoacoes.gerenciamento_doacoes.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DoacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDoacao; // roupas, alimentos, higiene
    private Integer quantidade;
    private LocalDate data;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusDoacao status;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    @JsonIdentityReference(alwaysAsId = false) // Garante que traga os dados do doador completos
    private DoadorEntity doador;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonIdentityReference(alwaysAsId = false) // Garante que traga os dados do pedido completos
    private PedidoEntity pedido;


    public DoacaoEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoDoacao() { return tipoDoacao; }
    public void setTipoDoacao(String tipoDoacao) { this.tipoDoacao = tipoDoacao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public StatusDoacao getStatus() { return status; }
    public void setStatus(StatusDoacao status) { this.status = status; }

    public DoadorEntity getDoador() { return doador; }
    public void setDoador(DoadorEntity doador) { this.doador = doador; }

    public PedidoEntity getPedido() { return pedido; }
    public void setPedido(PedidoEntity pedido) { this.pedido = pedido; }

    public enum StatusDoacao {
        PENDENTE,
        EM_ANDAMENTO,
        ENTREGUE,
        CANCELADA
    }
}

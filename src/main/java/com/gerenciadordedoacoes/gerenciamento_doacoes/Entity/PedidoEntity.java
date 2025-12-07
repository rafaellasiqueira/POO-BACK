package com.gerenciadordedoacoes.gerenciamento_doacoes.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoItem;
    private Integer quantidade;
    private String descricao;

    @ManyToOne
    private InstituicaoEntity instituicao;

    public PedidoEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoItem() { return tipoItem; }
    public void setTipoItem(String tipoItem) { this.tipoItem = tipoItem; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public InstituicaoEntity getInstituicao() { return instituicao; }
    public void setInstituicao(InstituicaoEntity instituicao) { this.instituicao = instituicao; }
}

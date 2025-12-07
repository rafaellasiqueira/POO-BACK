package com.gerenciadordedoacoes.gerenciamento_doacoes.Service;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoacaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.PedidoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.DoacaoRepository;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.DoadorRepository;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final DoadorRepository doadorRepository;
    private final PedidoRepository pedidoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository,
                         DoadorRepository doadorRepository,
                         PedidoRepository pedidoRepository) {
        this.doacaoRepository = doacaoRepository;
        this.doadorRepository = doadorRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // Registrar doação com doador e pedido completos
    public DoacaoEntity registrarDoacao(DoacaoEntity doacao) {

        // Buscar doador completo
        if (doacao.getDoador() != null && doacao.getDoador().getId() != null) {
            DoadorEntity doador = doadorRepository.findById(doacao.getDoador().getId())
                    .orElseThrow(() -> new RuntimeException("Doador não encontrado!"));
            doacao.setDoador(doador);
        } else {
            throw new RuntimeException("É obrigatório informar o ID do doador.");
        }

        // Buscar pedido completo
        if (doacao.getPedido() != null && doacao.getPedido().getId() != null) {
            PedidoEntity pedido = pedidoRepository.findById(doacao.getPedido().getId())
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
            doacao.setPedido(pedido);
        } else {
            throw new RuntimeException("É obrigatório informar o ID do pedido.");
        }

        // Status padrão
        if (doacao.getStatus() == null) {
            doacao.setStatus(DoacaoEntity.StatusDoacao.PENDENTE);
        }

        return doacaoRepository.save(doacao);
    }

    // Listar doações por ID do doador (carregando doador e pedido completos)
    public List<DoacaoEntity> listarDoacoesPorIdDoador(Long idDoador) {
        return doacaoRepository.findByDoadorId(idDoador);
    }

    // Buscar doação específica com doador e pedido completos
    public DoacaoEntity buscarDoacaoCompleta(Long id) {
        return doacaoRepository.findByIdWithDoadorAndPedido(id);
    }

    // Excluir doação
    public boolean excluirDoacao(Long id) {
        if (doacaoRepository.existsById(id)) {
            doacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

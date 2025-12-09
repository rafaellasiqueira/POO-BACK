package com.gerenciadordedoacoes.gerenciamento_doacoes.Service;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoacaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.PedidoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.DoacaoRepository;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.DoadorRepository;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public DoacaoEntity registrarDoacao(DoacaoEntity doacao) {

        if (doacao.getDoador() != null && doacao.getDoador().getId() != null) {
            DoadorEntity doador = doadorRepository.findById(doacao.getDoador().getId())
                    .orElseThrow(() -> new RuntimeException("Doador não encontrado!"));
            doacao.setDoador(doador);
        } else {
            throw new RuntimeException("É obrigatório informar o ID do doador.");
        }

        if (doacao.getPedido() != null && doacao.getPedido().getId() != null) {
            PedidoEntity pedido = pedidoRepository.findById(doacao.getPedido().getId())
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
            doacao.setPedido(pedido);
        } else {
            throw new RuntimeException("É obrigatório informar o ID do pedido.");
        }

        if (doacao.getStatus() == null) {
            doacao.setStatus(DoacaoEntity.StatusDoacao.PENDENTE);
        }

        return doacaoRepository.save(doacao);
    }

    public List<DoacaoEntity> listarDoacoesPorIdDoador(Long idDoador) {
        return doacaoRepository.findByDoadorId(idDoador);
    }

    public DoacaoEntity buscarDoacaoCompleta(Long id) {
        return doacaoRepository.findByIdWithDoadorAndPedido(id);
    }

    public boolean excluirDoacao(Long id) {
        if (doacaoRepository.existsById(id)) {
            doacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DoacaoEntity> listarDoacoesPorInstituicao(Long instituicaoId) {
        return doacaoRepository.findByInstituicaoId(instituicaoId);
    }

    public DoacaoEntity atualizarStatus(Long id, String novoStatus) {
        Optional<DoacaoEntity> doacaoOpt = doacaoRepository.findById(id);
        if (doacaoOpt.isPresent()) {
            DoacaoEntity doacao = doacaoOpt.get();
            doacao.setStatus(DoacaoEntity.StatusDoacao.valueOf(novoStatus));
            return doacaoRepository.save(doacao);
        }
        return null;
    }

}

package com.gerenciadordedoacoes.gerenciamento_doacoes.Service;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.PedidoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.InstituicaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.PedidoRepository;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final InstituicaoRepository instituicaoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         InstituicaoRepository instituicaoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.instituicaoRepository = instituicaoRepository;
    }

    public PedidoEntity criarPedido(PedidoEntity pedido) {

        if (pedido.getInstituicao() != null && pedido.getInstituicao().getId() != null) {

            InstituicaoEntity inst = instituicaoRepository.findById(pedido.getInstituicao().getId())
                    .orElseThrow(() -> new RuntimeException("Instituição não encontrada!"));

            pedido.setInstituicao(inst);
        } else {
            throw new RuntimeException("É obrigatório informar o ID da instituição.");
        }

        return pedidoRepository.save(pedido);
    }

    public List<PedidoEntity> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<PedidoEntity> listarPedidosPorInstituicao(InstituicaoEntity instituicao) {
        return pedidoRepository.findByInstituicao(instituicao);
    }

    public PedidoEntity buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public boolean deletarPedido(Long id) {
        if(pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PedidoEntity atualizarPedido(Long id, PedidoEntity dados) {
        Optional<PedidoEntity> optionalPedido = pedidoRepository.findById(id);

        if (optionalPedido.isPresent()) {
            PedidoEntity pedido = optionalPedido.get();

            pedido.setTipoItem(dados.getTipoItem());
            pedido.setQuantidade(dados.getQuantidade());
            pedido.setDescricao(dados.getDescricao());

            // recarrega instituição
            InstituicaoEntity inst = instituicaoRepository
                    .findById(dados.getInstituicao().getId())
                    .orElseThrow(() -> new RuntimeException("Instituição não encontrada!"));

            pedido.setInstituicao(inst);

            PedidoEntity salvo = pedidoRepository.save(pedido);
            salvo.setInstituicao(inst); // <- ESSA LINHA É O QUE RESOLVE
            return salvo;
        }
        return null;
    }
}


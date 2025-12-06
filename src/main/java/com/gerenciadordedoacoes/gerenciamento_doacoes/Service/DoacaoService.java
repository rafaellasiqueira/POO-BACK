package com.gerenciadordedoacoes.gerenciamento_doacoes.Service;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoacaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.DoacaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    public DoacaoEntity registrarDoacao(DoacaoEntity doacao) {
        return doacaoRepository.save(doacao);
    }

    public List<DoacaoEntity> listarDoacoesPorDoador(DoadorEntity doador) {
        return doacaoRepository.findByDoador(doador);
    }

    public List<DoacaoEntity> listarDoacoesPorIdDoador(Long idDoador) {
        return doacaoRepository.findByDoadorId(idDoador);
    }

    // Método para excluir a doação
    public boolean excluirDoacao(Long id) {
        Optional<DoacaoEntity> doacao = doacaoRepository.findById(id);
        if (doacao.isPresent()) {
            doacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}



package com.gerenciadordedoacoes.gerenciamento_doacoes.Service;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.InstituicaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.InstituicaoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoService(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    public InstituicaoEntity cadastrar(InstituicaoEntity instituicao) {
        return instituicaoRepository.save(instituicao);
    }

    public Optional<InstituicaoEntity> login(String email, String senha) {
        return instituicaoRepository.findByEmail(email)
                .filter(i -> i.getSenha().equals(senha));
    }

    public InstituicaoEntity atualizar(InstituicaoEntity instituicao) {
        return instituicaoRepository.save(instituicao);
    }

    public Optional<InstituicaoEntity> buscarPorId(Long id) {
        return instituicaoRepository.findById(id);
    }

}

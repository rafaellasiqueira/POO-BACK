package com.gerenciadordedoacoes.gerenciamento_doacoes.Controller;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.InstituicaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Service.InstituicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

    private final InstituicaoService instituicaoService;

    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @PostMapping("/add")
    public ResponseEntity<InstituicaoEntity> cadastrar(@RequestBody InstituicaoEntity instituicao) {
        return ResponseEntity.ok(instituicaoService.cadastrar(instituicao));
    }

    @PostMapping("/login")
    public ResponseEntity<InstituicaoEntity> login(@RequestBody InstituicaoEntity instituicao) {
        Optional<InstituicaoEntity> usuario = instituicaoService.login(instituicao.getEmail(), instituicao.getSenha());
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    @PutMapping("/update")
    public ResponseEntity<InstituicaoEntity> atualizar(@RequestBody InstituicaoEntity instituicao) {
        return ResponseEntity.ok(instituicaoService.atualizar(instituicao));
    }
}

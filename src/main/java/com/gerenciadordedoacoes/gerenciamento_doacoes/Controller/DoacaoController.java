package com.gerenciadordedoacoes.gerenciamento_doacoes.Controller;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoacaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Service.DoacaoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    // Registrar doação
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoacaoEntity> registrarDoacao(@RequestBody DoacaoEntity doacao) {
        DoacaoEntity salva = doacaoService.registrarDoacao(doacao);
        return ResponseEntity.ok(salva);
    }

    // Listar todas as doações de um doador pelo ID
    @GetMapping("/list/{id}")
    public ResponseEntity<List<DoacaoEntity>> listarPorDoador(@PathVariable Long id) {
        List<DoacaoEntity> doacoes = doacaoService.listarDoacoesPorIdDoador(id);
        return ResponseEntity.ok(doacoes);
    }

    // Deletar doação
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = doacaoService.excluirDoacao(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Listar todas as doações de uma instituição pelo ID
    @GetMapping("/listInstituicao/{id}")
    public ResponseEntity<List<DoacaoEntity>> listarPorInstituicao(@PathVariable Long id) {
        List<DoacaoEntity> doacoes = doacaoService.listarDoacoesPorInstituicao(id);
        return ResponseEntity.ok(doacoes);
    }

    // Atualizar status de uma doação
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<DoacaoEntity> atualizarStatus(@PathVariable Long id, @RequestBody StatusRequest statusRequest) {
        DoacaoEntity doacaoAtualizada = doacaoService.atualizarStatus(id, statusRequest.getStatus());
        if (doacaoAtualizada != null) {
            return ResponseEntity.ok(doacaoAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // Classe auxiliar para receber o status no corpo da requisição
    public static class StatusRequest {
        private String status;
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}

package br.com.appxpert.controller;

import br.com.appxpert.domain.movimentacao.MovimentacaoChave;
import br.com.appxpert.domain.movimentacao.MovimentacaoChaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoChaveController {

    @Autowired
    private MovimentacaoChaveRepository movimentacaoChaveRepository;

    // Listar movimentações por chave
    @GetMapping("/chaves/{chaveId}")
    public List<MovimentacaoChave> listarMovimentacoesPorChave(@PathVariable String chaveId) {
        return movimentacaoChaveRepository.findByChaveId(chaveId);
    }

    // Listar movimentações por usuário
    @GetMapping("/usuario/{usuarioId}")
    public List<MovimentacaoChave> listarMovimentacoesPorUsuario(@PathVariable String usuarioId) {
        return movimentacaoChaveRepository.findByUsuarioId(usuarioId);
    }


}

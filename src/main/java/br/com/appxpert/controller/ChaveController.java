package br.com.appxpert.controller;

import br.com.appxpert.domain.chave.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("chaves")
public class ChaveController {

    @Autowired
    private ChaveRepository repository;

    @PostMapping
    public ResponseEntity cadastrar (@RequestBody DadosCadastroChave dados, UriComponentsBuilder uriBuider) {
        var chave = new Chave(dados);
        repository.save(chave);
        var uri = uriBuider.path("/chaves/{id}").buildAndExpand(chave.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoChave(chave));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemChave>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemChave::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<DadosListagemChave>> listarPorNome(@PathVariable String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByNome(nome, paginacao).map(DadosListagemChave::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody DadosAtualizacaoChave dados) {
        var chaveOptional = repository.findById(dados.id());
        if (chaveOptional.isPresent()) {
            var chave = chaveOptional.get();
            chave.atualizarInformacoes(dados);
            repository.save(chave);
            return ResponseEntity.ok(new DadosDetalhamentoChave(chave));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        var optionalChave = repository.findById(id);

        if (optionalChave.isPresent()) {
            repository.deleteById(id); // Remove a entidade do MongoDB
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

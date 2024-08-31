package br.com.appxpert.controller;

import br.com.appxpert.domain.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity cadastrar (@RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dados);
        repository.save(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemUsuario::new);

        return ResponseEntity.ok(page);
    }

    /**
     * Endpoint para buscar usuários pelo nome.
     * Aceita um parâmetro de consulta `nome` que pode ser passado em maiúsculas ou minúsculas.
     * A busca é realizada ignorando o caso das letras.
     *
     * Exemplo de uso:
     * GET /buscar?nome=kennedy
     *
     * @param nome O nome do usuário a ser buscado. Pode ser em maiúsculas ou minúsculas.
     * @param paginacao Informações sobre a página e a ordenação dos resultados.
     * @return Uma página de usuários que correspondem ao nome fornecido.
     */
    @GetMapping("/buscar")
    public ResponseEntity<Page<DadosListagemUsuario>> buscarPorNome(@RequestParam String nome, @PageableDefault (size = 10, sort = "nome")Pageable paginacao) {
        var page = repository.findByNomeIgnoreCase(nome, paginacao).map(DadosListagemUsuario::new);

        return ResponseEntity.ok(page);
    }
}

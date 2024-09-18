package br.com.appxpert.domain.usuario;

import br.com.appxpert.domain.chave.DadosListagemChave;

import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemUsuario(
        String id,
        String nome,
        String sobreNome,
        String cpf,
        String telefone,
        String funcao,
        String setor,
        List<DadosListagemChave> chaves) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getSobreNome(), usuario.getCpf(), usuario.getTelefone(), usuario.getFuncao(), usuario.getSetor(), usuario.getChaves().stream().map(DadosListagemChave::new).collect(Collectors.toList()));
    }
}

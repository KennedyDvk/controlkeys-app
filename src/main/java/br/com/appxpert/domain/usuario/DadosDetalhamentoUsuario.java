package br.com.appxpert.domain.usuario;


import br.com.appxpert.domain.chave.DadosDetalhamentoChave;

import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoUsuario(
        String id,
        String nome,
        String sobreNome,
        String cpf,
        String telefone,
        String funcao,
        String setor,
        List<DadosDetalhamentoChave> chaves) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getSobreNome(), usuario.getCpf(), usuario.getTelefone(), usuario.getSetor(), usuario.getFuncao(), usuario.getChaves().stream().map(DadosDetalhamentoChave::new).collect(Collectors.toList()));

    }
}

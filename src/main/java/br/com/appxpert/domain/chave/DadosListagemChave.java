package br.com.appxpert.domain.chave;

import br.com.appxpert.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosListagemChave(String id, String nome, String numero, String descricao, boolean disponivel, String usuarioNome, String usuarioSetor, LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {

    public DadosListagemChave(Chave chave) {
        this(
                chave.getId(),
                chave.getNome(),
                chave.getNumero(),
                chave.getDescricao(),
                chave.isDisponivel(),
                chave.getUsuario() != null ? chave.getUsuario().getNome() : null,
                chave.getUsuario() != null ? chave.getUsuario().getSetor() : null,
                chave.getDataRetirada(),
                chave.getDataDevolucao());
    }
}

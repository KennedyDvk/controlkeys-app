package br.com.appxpert.domain.chave;

import java.time.LocalDateTime;

public record DadosDetalhamentoChave(String id, String nome, String numero, String descricao, boolean disponivel, String usuarioNome, String usuarioSetor, LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {

    public DadosDetalhamentoChave(Chave chave) {
        this(
                chave.getId(),
                chave.getNome(),
                chave.getNumero(),
                chave.getDescricao(),
                chave.isDisponivel(),
                chave.getUsuario() != null ? chave.getUsuario().getNome() : null,
                chave.getUsuario() != null ? chave.getUsuario().getSetor() : null,
                chave.getDataRetirada(),
                chave.getDataDevolucao()
        );
    }
}

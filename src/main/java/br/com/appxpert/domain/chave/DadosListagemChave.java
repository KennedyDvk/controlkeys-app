package br.com.appxpert.domain.chave;

import br.com.appxpert.domain.usuario.Usuario;

public record DadosListagemChave(String id, String nome, String numero, String descricao) {

    public DadosListagemChave(Chave chave) {
        this(chave.getId(), chave.getNome(), chave.getNumero(), chave.getDescricao());
    }
}

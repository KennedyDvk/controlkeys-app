package br.com.appxpert.domain.chave;

public record DadosListagemChave(String id, String nome, String numero, String descricao) {

    public DadosListagemChave(Chave chave) {
        this(chave.getId(), chave.getNome(), chave.getNumero(), chave.getDescricao());
    }
}

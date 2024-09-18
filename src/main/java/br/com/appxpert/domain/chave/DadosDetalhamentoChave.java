package br.com.appxpert.domain.chave;

public record DadosDetalhamentoChave(String id, String nome, String numero, String descricao, boolean disponivel) {

    public DadosDetalhamentoChave(Chave chave) {
        this(chave.getId(), chave.getNome(), chave.getNumero(), chave.getDescricao(), chave.isDisponivel());
    }
}

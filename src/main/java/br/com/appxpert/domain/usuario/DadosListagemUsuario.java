package br.com.appxpert.domain.usuario;

public record DadosListagemUsuario(String nome, String telefone, String setor, String funcao) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getNome(), usuario.getTelefone(), usuario.getSetor(), usuario.getFuncao());
    }
}

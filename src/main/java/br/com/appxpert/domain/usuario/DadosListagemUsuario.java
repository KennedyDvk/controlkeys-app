package br.com.appxpert.domain.usuario;

public record DadosListagemUsuario(String nome,String sobreNome, String telefone, String setor, String funcao) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getNome(), usuario.getSobreNome(), usuario.getTelefone(), usuario.getSetor(), usuario.getFuncao());
    }
}

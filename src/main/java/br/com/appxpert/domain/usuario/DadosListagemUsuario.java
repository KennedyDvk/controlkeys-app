package br.com.appxpert.domain.usuario;

public record DadosListagemUsuario(
        String nome,
        String sobreNome,
        String cpf,
        String telefone,
        String funcao,
        String setor) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getNome(), usuario.getSobreNome(), usuario.getCpf(), usuario.getTelefone(), usuario.getFuncao(), usuario.getSetor());
    }
}

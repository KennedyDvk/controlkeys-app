package br.com.appxpert.domain.usuario;


public record DadosDetalhamentoUsuario(String id, String nome, String telefone, String cpf, String setor, String funcao) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getTelefone(), usuario.getCpf(), usuario.getSetor(), usuario.getFuncao());

    }
}

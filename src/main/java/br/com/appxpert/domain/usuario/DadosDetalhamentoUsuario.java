package br.com.appxpert.domain.usuario;


public record DadosDetalhamentoUsuario(String id, String nome,String sobreNome, String cpf, String telefone, String funcao, String setor) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getSobreNome(), usuario.getCpf(), usuario.getTelefone(), usuario.getSetor(), usuario.getFuncao());

    }
}

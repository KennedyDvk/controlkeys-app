package br.com.appxpert.domain.usuario;

public record DadosCadastroUsuario(
        String nome,
        String sobreNome,
        String telefone,
        String cpf,
        String setor,
        String funcao) {

    public DadosCadastroUsuario(String nome, String sobreNome, String telefone, String cpf, String setor, String funcao) {
        this.nome = capitalize(nome);
        this.sobreNome = capitalize(sobreNome);
        this.telefone = telefone;
        this.cpf = cpf;
        this.setor = capitalize(setor);
        this.funcao = capitalize(funcao);
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
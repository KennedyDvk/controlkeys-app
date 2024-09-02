package br.com.appxpert.domain.usuario;

public record DadosAtualizacaoUsuario(
        String id,
        String nome,
        String sobreNome,
        String telefone,
        String cpf,
        String setor,
        String funcao
) {

    public DadosAtualizacaoUsuario {
        if (nome != null) {
            nome = capitalize(nome);
        }
        if (sobreNome != null) {
            sobreNome = capitalize(sobreNome);
        }
        if (setor != null) {
            setor = capitalize(setor);
        }
        if (funcao != null) {
            funcao = capitalize(funcao);
        }
    }

    private String capitalize(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }

}

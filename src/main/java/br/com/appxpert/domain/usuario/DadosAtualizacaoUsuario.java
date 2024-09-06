package br.com.appxpert.domain.usuario;

public record DadosAtualizacaoUsuario(
        String id,
        String nome,
        String sobreNome,
        String cpf,
        String telefone,
        String funcao,
        String setor
) {

    public DadosAtualizacaoUsuario {
        if (nome != null) {
            nome = capitalize(nome);
        }

        if (sobreNome != null) {
            sobreNome = capitalize(sobreNome);
        }

        if (funcao != null) {
            funcao = capitalize(funcao);
        }

        if (setor != null) {
            setor = capitalize(setor);
        }
    }

    private String capitalize(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }

}

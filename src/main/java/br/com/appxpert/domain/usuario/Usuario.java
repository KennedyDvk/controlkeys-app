package br.com.appxpert.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private String id;

    private String nome;

    private String sobreNome;

    private String cpf;

    private String telefone;

    private String funcao;

    private String setor;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.sobreNome = dados.sobreNome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.funcao = dados.funcao();
        this.setor = dados.setor();
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.sobreNome() != null) {
            this.sobreNome = dados.sobreNome();
        }

        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.funcao() != null) {
            this.funcao = dados.funcao();
        }

        if (dados.setor() != null) {
            this.setor = dados.setor();
        }

    }

}



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

    private String telefone;

    private String cpf;

    private String setor;

    private String funcao;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.setor = dados.setor();
        this.funcao = dados.funcao();
    }

//    public void setNome(String nome) {
//        this.nome = capitalize(nome);
//    }
//
//    private String capitalize(String str) {
//
//        if (str == null || str.isEmpty()) {
//            return str;
//        }
//        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
//    }

//    public static void main(String[] args) {
//        Usuario usuario = new Usuario();
//        usuario.setNome("KENNEDY");  // Definindo o nome todo em maiúsculas
//        System.out.println(usuario.getNome());  // Deve imprimir "Kennedy"
//    }


}



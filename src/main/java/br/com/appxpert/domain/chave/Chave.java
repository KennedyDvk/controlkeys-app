package br.com.appxpert.domain.chave;

import br.com.appxpert.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chave")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chave {

    @Id
    private String id;
    private String nome;
    private String numero;
    private String descricao;
    private boolean disponivel;
    private Usuario usuario;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataRetirada;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDevolucao;

    public Chave(DadosCadastroChave dados) {
        this.nome = dados.nome();
        this.numero = dados.numero();
        this.descricao = dados.descricao();
        this.disponivel = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoChave dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }

    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void marcarComoIndisponivel() {
        this.disponivel = false;
    }

    public void marcarComoDisponivel() {
        this.disponivel = true;
    }
}

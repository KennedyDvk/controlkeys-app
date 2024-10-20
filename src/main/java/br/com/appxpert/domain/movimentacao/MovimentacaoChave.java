package br.com.appxpert.domain.movimentacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "movimentacoesChave")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoChave {

    @Id
    private String id;
    private String usuarioId;
    private String usuarioNome;
    private String chaveId;
    private String chaveNome;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataRetirada;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDevolucao;
    private boolean devolvida;

}

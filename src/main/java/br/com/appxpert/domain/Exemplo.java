package br.com.appxpert.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exemplo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exemplo {

    @Id
    private String id;
    private String nome;

    public Exemplo(String name) {
    }
}

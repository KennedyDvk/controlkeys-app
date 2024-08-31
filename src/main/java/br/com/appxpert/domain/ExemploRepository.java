package br.com.appxpert.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExemploRepository extends MongoRepository <Exemplo, String>{
}

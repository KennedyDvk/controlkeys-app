package br.com.appxpert.controller;

import br.com.appxpert.domain.Exemplo;
import br.com.appxpert.domain.ExemploRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ExemploController {

    @Autowired
    private ExemploRepository repository;

    @PostMapping
    public ResponseEntity<Exemplo> inserir(@RequestBody Exemplo exemplo) {
        repository.save(exemplo);
        return ResponseEntity.created(null).body(exemplo);
    }


}

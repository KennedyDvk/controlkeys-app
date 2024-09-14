package br.com.appxpert.domain.chave;

import br.com.appxpert.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChaveRepository extends MongoRepository<Chave, String> {
    Page<Chave> findAllByNome(String nome, Pageable paginacao);
    Page<Chave> findAll(Pageable paginacao);
}

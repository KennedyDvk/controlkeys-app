package br.com.appxpert.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Page<Usuario> findAll(Pageable paginacao);
//    Page<Usuario> findAllByNome(String nome, Pageable paginacao);
    @Query("{ 'nome': { $regex: ?0, $options: 'i' } }")
    Page<Usuario> findByNomeIgnoreCase(String nome, Pageable paginacao);
}

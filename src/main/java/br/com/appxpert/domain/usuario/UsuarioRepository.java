package br.com.appxpert.domain.usuario;

import br.com.appxpert.domain.chave.Chave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Page<Usuario> findAll(Pageable paginacao);

    @Query("{ $or: [ { 'nome': { $regex: ?0, $options: 'i' } }, " +
            "{ 'sobreNome': { $regex: ?0, $options: 'i' } } ] }")
    Page<Usuario> findByNomeIgnoreCase(String nome, Pageable paginacao);

    @Query("{ 'setor': { $regex: ?0, $options: 'i' } }")
    Page<Usuario>findBySetorIgnoreCase(String setor, Pageable paginacao);

    @Query("{ 'funcao': { $regex: ?0, $options: 'i' } }")
    Page<Usuario>findByFuncaoIgnoreCase(String setor, Pageable paginacao);
}

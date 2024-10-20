package br.com.appxpert.domain.movimentacao;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovimentacaoChaveRepository extends MongoRepository<MovimentacaoChave, String> {
    List<MovimentacaoChave> findByChaveId(String chaveId);
    List<MovimentacaoChave> findByUsuarioId(String usuarioId);
}

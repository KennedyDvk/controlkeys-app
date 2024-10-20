package br.com.appxpert.service.usuario;

import br.com.appxpert.domain.chave.Chave;
import br.com.appxpert.domain.chave.ChaveRepository;
import br.com.appxpert.domain.movimentacao.MovimentacaoChave;
import br.com.appxpert.domain.movimentacao.MovimentacaoChaveRepository;
import br.com.appxpert.domain.usuario.Usuario;
import br.com.appxpert.domain.usuario.UsuarioRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioService {

    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ChaveRepository chaveRepository;

    @Autowired
    private MovimentacaoChaveRepository movimentacaoChaveRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Chave saveChave(Chave chave) {
        return chaveRepository.save(chave);
    }

    public void associarChaveAoUsuario(String usuarioId, String chaveId, LocalDateTime dataRetirada) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Chave> chaveOpt = chaveRepository.findById(chaveId);

        if (usuarioOpt.isPresent() && chaveOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Chave chave = chaveOpt.get();

            if (chave.isDisponivel()) {
                chave.setDisponivel(false);
                chave.setUsuario(usuario);
                chaveRepository.save(chave);

                MovimentacaoChave movimentacao = new MovimentacaoChave();
                movimentacao.setUsuarioId(usuario.getId());
                movimentacao.setUsuarioNome(usuario.getNome());
                movimentacao.setChaveId(chave.getId());
                movimentacao.setChaveNome(chave.getNome());
                movimentacao.setDataRetirada(dataRetirada);
                movimentacao.setDevolvida(false);
                movimentacaoChaveRepository.save(movimentacao);

                usuario.getChaves().add(chave);
                usuarioRepository.save(usuario);
            }
        }
    }


    public void devolverChave(String usuarioId, String chaveId, LocalDateTime dataDevolucao) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Chave> chaveOpt = chaveRepository.findById(chaveId);

        if (usuarioOpt.isPresent() && chaveOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Chave chave = chaveOpt.get();

            usuario.getChaves().removeIf(c -> c.getId().equals(chave.getId()));
            chave.setDisponivel(true);

            // Atualizar o log de movimentação
            List<MovimentacaoChave> movimentacoes = movimentacaoChaveRepository.findByChaveId(chaveId);
            for (MovimentacaoChave movimentacao : movimentacoes) {
                if (!movimentacao.isDevolvida()) {
                    movimentacao.setDataDevolucao(dataDevolucao);
                    movimentacao.setDevolvida(true);
                    movimentacaoChaveRepository.save(movimentacao);
                    break;
                }
            }

            chaveRepository.save(chave);
            usuarioRepository.save(usuario);
        }
    }
}
package br.com.appxpert.service.usuario;

import br.com.appxpert.domain.chave.Chave;
import br.com.appxpert.domain.chave.ChaveRepository;
import br.com.appxpert.domain.usuario.Usuario;
import br.com.appxpert.domain.usuario.UsuarioRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioService {

    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ChaveRepository chaveRepository;

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
                chave.setUsuario(usuario); // Definindo o usuário associado
                chave.setDataRetirada(dataRetirada); // Definindo a data de retirada
                chaveRepository.save(chave);

                if (usuario.getChaves() == null) {
                    usuario.setChaves(new ArrayList<>());
                }
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

            logger.info("Usuário antes da devolução: " + usuario.getChaves());

            // Remover a chave da lista do usuário usando o ID para comparar
            usuario.getChaves().removeIf(c -> c.getId().equals(chave.getId()));

            // Atualizar a chave para estar disponível e definir a data de devolução
            chave.setDisponivel(true);
            chave.setDataDevolucao(dataDevolucao); // Definir a data de devolução

            logger.info("Usuário após a devolução: " + usuario.getChaves());

            // Salvar as alterações
            chaveRepository.save(chave);
            usuarioRepository.save(usuario);
            logger.info("Chave devolvida com sucesso.");
        }
    }

}
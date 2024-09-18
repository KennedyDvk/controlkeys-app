package br.com.appxpert.service.usuario;

import br.com.appxpert.domain.chave.Chave;
import br.com.appxpert.domain.chave.ChaveRepository;
import br.com.appxpert.domain.usuario.Usuario;
import br.com.appxpert.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {

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

    public void associarChaveAoUsuario(String usuarioId, String chaveId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Chave> chaveOpt = chaveRepository.findById(chaveId);

        if (usuarioOpt.isPresent() && chaveOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Chave chave = chaveOpt.get();

            if (chave.isDisponivel()) {
                chave.setDisponivel(false);
                chaveRepository.save(chave);

                usuario.getChaves().add(chave); // Adiciona a chave ao usuário
                usuarioRepository.save(usuario); // Salva o usuário com a chave associada
            }
        }
    }

    public void devolverChave(String usuarioId, String chaveId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Chave> chaveOpt = chaveRepository.findById(chaveId);

        if (usuarioOpt.isPresent() && chaveOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Chave chave = chaveOpt.get();

            if (usuario.getChaves().remove(chave)) {
                chave.setDisponivel(true);
                chaveRepository.save(chave);
                usuarioRepository.save(usuario);
            }
        }
    }
}
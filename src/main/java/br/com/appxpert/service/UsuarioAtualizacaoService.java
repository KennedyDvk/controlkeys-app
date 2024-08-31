package br.com.appxpert.service;

import br.com.appxpert.domain.usuario.Usuario;
import br.com.appxpert.domain.usuario.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioAtualizacaoService {

    @Autowired
    private UsuarioRepository repository;

    @PostConstruct
    public void atualizarNomes() {
        var usuarios = repository.findAll();
        for (Usuario usuario : usuarios) {
            String nomeCapitalizado = capitalize(usuario.getNome());
            usuario.setNome(nomeCapitalizado);
            repository.save(usuario);
        }
    }

    @Transactional
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

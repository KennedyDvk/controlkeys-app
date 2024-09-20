package br.com.appxpert.service.chave;

import br.com.appxpert.domain.chave.ChaveRepository;
import br.com.appxpert.domain.chave.DadosListagemChave;
import br.com.appxpert.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChaveService {

    @Autowired
    private ChaveRepository chaveRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<DadosListagemChave> listarChaves(Pageable paginacao) {
        return chaveRepository.findAll(paginacao).map(DadosListagemChave::new);
    }
}


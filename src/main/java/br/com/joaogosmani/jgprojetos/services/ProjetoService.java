package br.com.joaogosmani.jgprojetos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.jgprojetos.api.dto.ProjetoDTO;
import br.com.joaogosmani.jgprojetos.api.mappers.ProjetoMapper;
import br.com.joaogosmani.jgprojetos.exceptions.ProjetoNaoEncontradoException;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import br.com.joaogosmani.jgprojetos.repositories.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoMapper projetoMapper;

    public List<Projeto> buscarTodos() {
        return projetoRepository.findAll();
    }

    public Page<Projeto> buscarTodos(Pageable paginacao) {
        return projetoRepository.findAll(paginacao);
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(id));
    }

    public Projeto cadastrar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto cadastrar(ProjetoDTO projetoDTO) {
        Projeto projeto = projetoMapper.converterParaEntidade(projetoDTO);

        return projetoRepository.save(projeto);
    }

    public Projeto atualizar(Projeto projeto, Long id) {
        buscarPorId(id);

        return projetoRepository.save(projeto);
    }

    public void excluirPorId(Long id) {
        Projeto projetoEncontrado = buscarPorId(id);

        projetoRepository.delete(projetoEncontrado);
    }

}

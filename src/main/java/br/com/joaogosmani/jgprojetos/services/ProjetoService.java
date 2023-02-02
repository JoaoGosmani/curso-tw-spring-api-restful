package br.com.joaogosmani.jgprojetos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.jgprojetos.api.dto.EquipeDTO;
import br.com.joaogosmani.jgprojetos.api.dto.ProjetoDTO;
import br.com.joaogosmani.jgprojetos.api.mappers.ProjetoMapper;
import br.com.joaogosmani.jgprojetos.exceptions.ProjetoNaoEncontradoException;
import br.com.joaogosmani.jgprojetos.models.Funcionario;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import br.com.joaogosmani.jgprojetos.repositories.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoMapper projetoMapper;

    @Autowired
    private FuncionarioService funcionarioService;

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

    public Projeto atualizar(ProjetoDTO projetoDTO, Long id) {
        buscarPorId(id);

        Projeto projeto = projetoMapper.converterParaEntidade(projetoDTO);
        projeto.setId(id);

        return projetoRepository.save(projeto);
    }

    public List<Funcionario> atualizarEquipe(EquipeDTO equipeDTO, Long id) {
        Projeto projeto = buscarPorId(id);

        List<Funcionario> equipe = new ArrayList<>();

        equipeDTO.getEquipe().forEach(funcionarioId -> {
            Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId);

                equipe.add(funcionario);
        });

            projeto.setEquipe(equipe);
            projetoRepository.save(projeto);

            return equipe;
    }

    public void excluirPorId(Long id) {
        Projeto projetoEncontrado = buscarPorId(id);

        projetoRepository.delete(projetoEncontrado);
    }

}

package br.com.joaogosmani.jgprojetos.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.jgprojetos.api.docs.FuncionarioControllerApiDoc;
import br.com.joaogosmani.jgprojetos.api.hateoas.FuncionarioAssembler;
import br.com.joaogosmani.jgprojetos.api.hateoas.ProjetoAssembler;
import br.com.joaogosmani.jgprojetos.models.Funcionario;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import br.com.joaogosmani.jgprojetos.services.FuncionarioService;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioControllerApi implements FuncionarioControllerApiDoc {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private PagedResourcesAssembler<Funcionario> pagedResourcesAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Funcionario>> buscarTodos(Pageable paginacao) {
        Page<Funcionario> funcionarios = funcionarioService.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(funcionarios, funcionarioAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Funcionario> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);

        return funcionarioAssembler.toModel(funcionario);
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> buscarProjetos(@PathVariable Long id) {
        List<Projeto> projetos = funcionarioService.buscarPorId(id).getProjetos();

        return projetoAssembler.toCollectionModel(projetos);
    }

}

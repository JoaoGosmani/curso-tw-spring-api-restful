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

import br.com.joaogosmani.jgprojetos.api.docs.ClienteControllerApiDoc;
import br.com.joaogosmani.jgprojetos.api.hateoas.ClienteAssembler;
import br.com.joaogosmani.jgprojetos.api.hateoas.ProjetoAssembler;
import br.com.joaogosmani.jgprojetos.models.Cliente;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import br.com.joaogosmani.jgprojetos.services.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteControllerApi implements ClienteControllerApiDoc {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteAssembler clienteAssembler;

    @Autowired
    private PagedResourcesAssembler<Cliente> pagedResourcesAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Cliente>> buscarTodos(Pageable paginacao) {
        Page<Cliente> clientes = clienteService.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(clientes, clienteAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);

        return clienteAssembler.toModel(cliente);
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> buscarProjetos(@PathVariable Long id) {
        List<Projeto> projetos = clienteService.buscarPorId(id).getProjetos();

        return projetoAssembler.toCollectionModel(projetos);
    }

}

package br.com.joaogosmani.jgprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.jgprojetos.api.controllers.ClienteControllerApi;
import br.com.joaogosmani.jgprojetos.api.controllers.FuncionarioControllerApi;
import br.com.joaogosmani.jgprojetos.api.controllers.ProjetoControllerApi;
import br.com.joaogosmani.jgprojetos.models.Projeto;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

    @Override
    public void addLinks(EntityModel<Projeto> resource) {
        Long clienteId = resource.getContent().getCliente().getId();
        Long liderId = resource.getContent().getLider().getId();
        Long id = resource.getContent().getId();

        Link liderLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarPorId(liderId))
            .withRel("lider")
            .withType("GET");
        
        Link clienteLink = linkTo(methodOn(ClienteControllerApi.class).buscarPorId(clienteId))
            .withRel("cliente")
            .withType("GET");

        Link selfLink = linkTo(methodOn(ProjetoControllerApi.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

        Link editarLink = linkTo(methodOn(ProjetoControllerApi.class).atualizar(null, id))
            .withSelfRel()
            .withType("PUT");

        Link excluirLink = linkTo(methodOn(ProjetoControllerApi.class).excluirPorId(id))
            .withSelfRel()
            .withType("DELETE");

        Link equipeLink = linkTo(methodOn(ProjetoControllerApi.class).buscarEquipe(id))
            .withRel("equipe")
            .withType("GET");

        Link atualizarEquipeLink = linkTo(methodOn(ProjetoControllerApi.class).atualizarEquipe(id, null))
            .withRel("equipe")
            .withType("PATCH");

        resource.add(liderLink, clienteLink, selfLink, editarLink, excluirLink, equipeLink, atualizarEquipeLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        Link selfLink = linkTo(methodOn(ProjetoControllerApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        Link cadastroLink = linkTo(methodOn(ProjetoControllerApi.class).cadastrar(null))
            .withSelfRel()
            .withType("POST");

        resources.add(selfLink, cadastroLink);
    }
    
}

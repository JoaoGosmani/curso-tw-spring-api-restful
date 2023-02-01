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

        Link liderLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarPorId(liderId))
            .withRel("lider")
            .withType("GET");
        
        Link clienteLink = linkTo(methodOn(ClienteControllerApi.class).buscarPorId(clienteId))
            .withRel("cliente")
            .withType("GET");

        resource.add(liderLink, clienteLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        Link selfLink = linkTo(methodOn(ProjetoControllerApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink);
    }
    
}

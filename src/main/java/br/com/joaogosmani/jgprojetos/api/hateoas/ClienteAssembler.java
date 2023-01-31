package br.com.joaogosmani.jgprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.jgprojetos.api.controllers.ClienteControllerApi;
import br.com.joaogosmani.jgprojetos.models.Cliente;

@Component
public class ClienteAssembler implements SimpleRepresentationModelAssembler<Cliente> {

    @Override
    public void addLinks(EntityModel<Cliente> resource) {
        Long id = resource.getContent().getId();

        Link selfLink = linkTo(methodOn(ClienteControllerApi.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

        resource.add(selfLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cliente>> resources) {
        Link selfLink = linkTo(methodOn(ClienteControllerApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink);
    }
    
}

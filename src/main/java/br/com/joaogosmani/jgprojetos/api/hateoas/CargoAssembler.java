package br.com.joaogosmani.jgprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.jgprojetos.api.controllers.CargoControllerApi;
import br.com.joaogosmani.jgprojetos.models.Cargo;

@Component
public class CargoAssembler implements SimpleRepresentationModelAssembler<Cargo>{

    @Override
    public void addLinks(EntityModel<Cargo> resource) {
        Long id = resource.getContent().getId();

        Link selfLink = linkTo(methodOn(CargoControllerApi.class).buscarPorId(id))
            .withSelfRel()
                .withType("GET");

        Link editarLink = linkTo(methodOn(CargoControllerApi.class).atualizar(null, id))
            .withSelfRel()
            .withType("PUT");

        Link excluirLink = linkTo(methodOn(CargoControllerApi.class).excluirPorId(id))
            .withSelfRel()
            .withType("DELETE");

        resource.add(selfLink, editarLink, excluirLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cargo>> resources) {
        Link cadastroLink = linkTo(methodOn(CargoControllerApi.class).cadastrar(null))
            .withSelfRel()
            .withType("POST");

        Link selfLink = linkTo(methodOn(CargoControllerApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink, cadastroLink);
    }

}

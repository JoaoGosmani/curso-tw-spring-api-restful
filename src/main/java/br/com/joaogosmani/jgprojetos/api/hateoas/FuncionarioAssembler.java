package br.com.joaogosmani.jgprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.jgprojetos.api.controllers.CargoControllerApi;
import br.com.joaogosmani.jgprojetos.api.controllers.FuncionarioControllerApi;
import br.com.joaogosmani.jgprojetos.models.Funcionario;

@Component
public class FuncionarioAssembler implements SimpleRepresentationModelAssembler<Funcionario> {

    @Override
    public void addLinks(EntityModel<Funcionario> resource) {
        Long cargoId = resource.getContent().getCargo().getId();
        Long id = resource.getContent().getId();

        Link cargoLink = linkTo(methodOn(CargoControllerApi.class).buscarPorId(cargoId))
            .withRel("cargo")
            .withType("GET");

        Link selfLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

        resource.add(cargoLink, selfLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Funcionario>> resources) {
        Link selfLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink);
    }
    
}

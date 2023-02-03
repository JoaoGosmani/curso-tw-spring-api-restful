package br.com.joaogosmani.jgprojetos.api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.jgprojetos.api.docs.HomeControllerApiDoc;
import br.com.joaogosmani.jgprojetos.api.hateoas.HomeModel;

@RestController
@RequestMapping("/api/v1")
public class HomeControllerApi implements HomeControllerApiDoc {
    
    @GetMapping
    public HomeModel home() {
        HomeModel homeModel = new HomeModel();

        Link cargosLink = linkTo(methodOn(CargoControllerApi.class).buscarTodos(null))
            .withRel("cargos")
            .withType("GET");

        Link clientesLink = linkTo(methodOn(ClienteControllerApi.class).buscarTodos(null))
            .withRel("clientes")
            .withType("GET");

        Link funcionariosLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarTodos(null))
            .withRel("funcionarios")
            .withType("GET");

        Link projetosLink = linkTo(methodOn(ProjetoControllerApi.class).buscarTodos(null))
            .withRel("projetos")
            .withType("GET");

        homeModel.add(cargosLink, clientesLink, funcionariosLink, projetosLink);

        return homeModel;
    }

}

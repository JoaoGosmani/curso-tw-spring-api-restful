package br.com.joaogosmani.jgprojetos.api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.jgprojetos.api.hateoas.HomeModel;

@RestController
@RequestMapping("/api/v1")
public class HomeControllerApi {
    
    @GetMapping
    public HomeModel home() {
        HomeModel homeModel = new HomeModel();

        Link cargosLink = linkTo(methodOn(CargoControllerApi.class).buscarTodos())
            .withRel("cargos")
            .withType("GET");

        homeModel.add(cargosLink);

        return homeModel;
    }

}

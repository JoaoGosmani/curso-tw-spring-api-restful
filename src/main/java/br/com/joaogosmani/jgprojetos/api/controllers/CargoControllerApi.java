package br.com.joaogosmani.jgprojetos.api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.jgprojetos.api.dto.CargoDTO;
import br.com.joaogosmani.jgprojetos.models.Cargo;
import br.com.joaogosmani.jgprojetos.services.CargoService;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControllerApi {
    
    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<Cargo> buscarTodos() {
        List<Cargo> cargos = cargoService.buscarTodos();

        cargos.forEach(cargo -> {
            Long id = cargo.getId();

            Link selfLink = linkTo(methodOn(CargoControllerApi.class).buscarPorId(id))
                .withSelfRel()
                .withType("GET");

            Link editarLink = linkTo(methodOn(CargoControllerApi.class).atualizar(null, id))
                .withSelfRel()
                .withType("PUT");

                Link excluirLink = linkTo(methodOn(CargoControllerApi.class).excluirPorId(id))
                    .withSelfRel()
                    .withType("DELETE");

            cargo.add(selfLink, editarLink, excluirLink);
        });

        return cargos;
    }

    @GetMapping("/{id}")
    public Cargo buscarPorId(@PathVariable Long id) {
        return cargoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cargo cadastrar(@RequestBody @Valid CargoDTO cargoDTO) {
        return cargoService.cadastrar(cargoDTO);
    }

    @PutMapping("/{id}")
    public Cargo atualizar(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
        return cargoService.atualizar(cargoDTO, id);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
        cargoService.excluirPorId(id);

            return ResponseEntity.noContent().build();
    }

}

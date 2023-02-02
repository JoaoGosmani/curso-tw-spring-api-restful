package br.com.joaogosmani.jgprojetos.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import br.com.joaogosmani.jgprojetos.api.docs.CargoControllerApiDoc;
import br.com.joaogosmani.jgprojetos.api.dto.CargoDTO;
import br.com.joaogosmani.jgprojetos.api.hateoas.CargoAssembler;
import br.com.joaogosmani.jgprojetos.models.Cargo;
import br.com.joaogosmani.jgprojetos.services.CargoService;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControllerApi implements CargoControllerApiDoc {
    
    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoAssembler cargoAssembler;

    @Autowired
    private PagedResourcesAssembler<Cargo> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Cargo>> buscarTodos(Pageable paginacao) {
        Page<Cargo> cargos = cargoService.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(cargos, cargoAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Cargo> buscarPorId(@PathVariable Long id) {
        Cargo cargo = cargoService.buscarPorId(id);

        return cargoAssembler.toModel(cargo);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Cargo> cadastrar(@RequestBody @Valid CargoDTO cargoDTO) {
        Cargo cargo = cargoService.cadastrar(cargoDTO);

        return cargoAssembler.toModel(cargo);
    }

    @PutMapping("/{id}")
    public EntityModel<Cargo> atualizar(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
        Cargo cargo = cargoService.atualizar(cargoDTO, id);

        return cargoAssembler.toModel(cargo);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
        cargoService.excluirPorId(id);

            return ResponseEntity.noContent().build();
    }

}

package br.com.joaogosmani.jgprojetos.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.jgprojetos.models.Cargo;
import br.com.joaogosmani.jgprojetos.services.CargoService;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControllerApi {
    
    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<Cargo> buscarTodos() {
        return cargoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Cargo buscarPorId(@PathVariable Long id) {
        return cargoService.buscarPorId(id);
    }

}

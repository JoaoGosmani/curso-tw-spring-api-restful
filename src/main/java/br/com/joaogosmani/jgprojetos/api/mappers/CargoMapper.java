package br.com.joaogosmani.jgprojetos.api.mappers;

import org.springframework.stereotype.Component;

import br.com.joaogosmani.jgprojetos.api.dto.CargoDTO;
import br.com.joaogosmani.jgprojetos.models.Cargo;

@Component
public class CargoMapper {
    
    public Cargo converterParaEntidade(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();

        cargo.setNome(cargoDTO.getNome());

        return cargo;
    }

}

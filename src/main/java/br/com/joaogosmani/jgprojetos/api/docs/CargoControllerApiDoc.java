package br.com.joaogosmani.jgprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import br.com.joaogosmani.jgprojetos.api.annotations.ApiPageable;
import br.com.joaogosmani.jgprojetos.api.dto.CargoDTO;
import br.com.joaogosmani.jgprojetos.api.exceptions.ApiErro;
import br.com.joaogosmani.jgprojetos.api.exceptions.ValidacaoApiErro;
import br.com.joaogosmani.jgprojetos.models.Cargo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Cargos", description = "Cargo Controller")
public interface CargoControllerApiDoc {
    
    @ApiOperation(value = "Listar todos os cargos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos cargos realizada com sucesso")
    })
    @ApiPageable
    CollectionModel<EntityModel<Cargo>> buscarTodos(@ApiIgnore Pageable paginacao);

    @ApiOperation(value = "Buscar cargo por id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo encontrado com sucesso"),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    EntityModel<Cargo> buscarPorId(Long id);

    @ApiOperation(value = "Cadastrar cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cargo cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidacaoApiErro.class)
    })
    EntityModel<Cargo> cadastrar(CargoDTO cargoDTO);

    @ApiOperation(value = "Atualizar cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidacaoApiErro.class),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    EntityModel<Cargo> atualizar(CargoDTO cargoDTO, Long id);

    @ApiOperation(value = "Excluir cargo por id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Cargo excluído com sucesso"),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    ResponseEntity<?> excluirPorId(Long id);

}

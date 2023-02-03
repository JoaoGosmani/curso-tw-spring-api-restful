package br.com.joaogosmani.jgprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import br.com.joaogosmani.jgprojetos.api.annotations.ApiPageable;
import br.com.joaogosmani.jgprojetos.models.Funcionario;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Funcionarios", description = "Funcionario Controller")
public interface FuncionarioControllerApiDoc {
    
    @ApiOperation(value = "Listar todos os funcionários")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos funcionários realizada com sucesso")
    })
    @ApiPageable
    CollectionModel<EntityModel<Funcionario>> buscarTodos(@ApiIgnore Pageable paginacao);

    @ApiOperation(value = "Buscar funcionário por id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Funcionário encontrado com sucesso"),
        @ApiResponse(code = 404, message = "Funcionário não encontrado")
    })
    EntityModel<Funcionario> buscarPorId(Long id);

    @ApiOperation(value = "Listar todos os projetos do funcionário")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos projetos do funcionário realizada com sucesso")
    })
    CollectionModel<EntityModel<Projeto>> buscarProjetos(Long id);

}

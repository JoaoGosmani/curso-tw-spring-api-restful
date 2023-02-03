package br.com.joaogosmani.jgprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import br.com.joaogosmani.jgprojetos.api.annotations.ApiPageable;
import br.com.joaogosmani.jgprojetos.api.exceptions.ApiErro;
import br.com.joaogosmani.jgprojetos.models.Cliente;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Clientes", description = "Cliente Controller")
public interface ClienteControllerApiDoc {
    
    @ApiOperation(value = "Listar todos os clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos clientes realizada com sucesso")
    })
    @ApiPageable
    CollectionModel<EntityModel<Cliente>> buscarTodos(@ApiIgnore Pageable paginacao);

    @ApiOperation(value = "Buscar cliente por id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
        @ApiResponse(code = 404, message = "Cliente não encontrado", response = ApiErro.class)
    })
    EntityModel<Cliente> buscarPorId(Long id);

    @ApiOperation(value = "Listar todos os projetos do cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos projetos do cliente realizada com sucesso")
    })
    CollectionModel<EntityModel<Projeto>> buscarProjetos(Long id);

}

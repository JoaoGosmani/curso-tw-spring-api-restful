package br.com.joaogosmani.jgprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import br.com.joaogosmani.jgprojetos.api.annotations.ApiPageable;
import br.com.joaogosmani.jgprojetos.api.dto.EquipeDTO;
import br.com.joaogosmani.jgprojetos.api.dto.ProjetoDTO;
import br.com.joaogosmani.jgprojetos.api.exceptions.ApiErro;
import br.com.joaogosmani.jgprojetos.api.exceptions.ValidacaoApiErro;
import br.com.joaogosmani.jgprojetos.models.Funcionario;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Projetos", description = "Projeto Controller")
public interface ProjetoControllerApiDoc {
    
    @ApiOperation(value = "Listar todos os projetos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos projetos realizada com sucesso")
    })
    @ApiPageable
    CollectionModel<EntityModel<Projeto>> buscarTodos(@ApiIgnore Pageable paginacao);

    @ApiOperation(value = "Buscar projeto por id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Projeto encontrado com sucesso"),
        @ApiResponse(code = 404, message = "Projeto não encontrado")
    })
    EntityModel<Projeto> buscarPorId(Long id);

    @ApiOperation(value = "Cadastrar projeto")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Projeto cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidacaoApiErro.class)
    })
    EntityModel<Projeto> cadastrar(ProjetoDTO projetoDTO);

    @ApiOperation(value = "Atualizar projeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Projeto atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidacaoApiErro.class),
        @ApiResponse(code = 404, message = "Projeto não encontrado", response = ApiErro.class)
    })
    EntityModel<Projeto> atualizar(ProjetoDTO projetoDTO, Long id);

    @ApiOperation(value = "Excluir projeto por id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Cargo excluído com sucesso"),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    ResponseEntity<?> excluirPorId(Long id);

    @ApiOperation(value = "Listar todos os funcionários do projeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos funcionários do projeto realizada com sucesso"),
        @ApiResponse(code = 404, message = "Projeto não encontrado", response = ApiErro.class)
    })
    CollectionModel<EntityModel<Funcionario>> buscarEquipe(Long id);

    @ApiOperation(value = "Atualizar os funcionários do projeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Funcionário(s) do projeto atualizado(s) com sucesso"),
        @ApiResponse(code = 404, message = "Projeto não encontrado", response = ApiErro.class)
    })
    CollectionModel<EntityModel<Funcionario>> atualizarEquipe(Long id, EquipeDTO equipeDTO);

}

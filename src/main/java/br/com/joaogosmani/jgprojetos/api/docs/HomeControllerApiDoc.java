package br.com.joaogosmani.jgprojetos.api.docs;

import br.com.joaogosmani.jgprojetos.api.hateoas.HomeModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Home", description = "Home Controller")
public interface HomeControllerApiDoc {
    
    @ApiOperation(value = "Exibe a página home da aplicação")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Página home encontrada com sucesso")
    })
    HomeModel home();

}

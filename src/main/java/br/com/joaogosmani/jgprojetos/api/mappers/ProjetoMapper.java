package br.com.joaogosmani.jgprojetos.api.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.jgprojetos.api.dto.ProjetoDTO;
import br.com.joaogosmani.jgprojetos.models.Projeto;
import br.com.joaogosmani.jgprojetos.services.ClienteService;
import br.com.joaogosmani.jgprojetos.services.FuncionarioService;

@Component
public class ProjetoMapper {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ClienteService clienteService;
    
    public Projeto converterParaEntidade(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();

        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setOrcamento(projetoDTO.getOrcamento());
        projeto.setGastos(projetoDTO.getGastos());
        
        projeto.setCliente(clienteService.buscarPorId(projetoDTO.getClienteId()));
        projeto.setLider(funcionarioService.buscarPorId(projetoDTO.getLiderId()));

        return projeto;
    }

}

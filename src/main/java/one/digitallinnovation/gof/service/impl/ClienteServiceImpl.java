package one.digitallinnovation.gof.service.impl;


import one.digitallinnovation.gof.config.exception.ExceptionCliente;
import one.digitallinnovation.gof.model.ClienteRepository;
import one.digitallinnovation.gof.model.EnderecoRepository;
import one.digitallinnovation.gof.model.entities.Cliente;
import one.digitallinnovation.gof.model.entities.Endereco;
import one.digitallinnovation.gof.service.ClienteService;
import one.digitallinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;


    private final EnderecoRepository enderecoRepository;


    private final ViaCepService viaCepService;




    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ViaCepService viaCepService) {

        this.enderecoRepository = enderecoRepository;

        this.clienteRepository = clienteRepository;

        this.viaCepService = viaCepService;
    }



    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {

      return clienteRepository.findById(id).orElseThrow(()->new ExceptionCliente("Cliente não encontrado!"));

    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClienteComCep(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}

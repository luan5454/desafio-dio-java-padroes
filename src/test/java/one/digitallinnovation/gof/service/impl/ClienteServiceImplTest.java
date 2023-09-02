package one.digitallinnovation.gof.service.impl;

import one.digitallinnovation.gof.model.ClienteRepository;
import one.digitallinnovation.gof.model.EnderecoRepository;
import one.digitallinnovation.gof.model.entities.Cliente;
import one.digitallinnovation.gof.model.entities.Endereco;
import one.digitallinnovation.gof.service.ClienteService;
import one.digitallinnovation.gof.service.ViaCepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class ClienteServiceImplTest {


    ClienteService clienteService;
    private final ViaCepService viaCepService = Mockito.mock(ViaCepService.class);
    private final EnderecoRepository enderecaoRepository = Mockito.mock(EnderecoRepository.class);
    private final ClienteRepository clienteReposotory = Mockito.mock(ClienteRepository.class);

    @BeforeEach
    void setUp() {
        clienteService = new ClienteServiceImpl(clienteReposotory, enderecaoRepository, viaCepService);
    }

    @Test
    @DisplayName("Deve adicionar um Cliente")
    void deveAdicionarUmCliente() {

        Cliente cliente01 = new Cliente();
        cliente01.setNome("Andre");
        cliente01.setEndereco(new Endereco());
        cliente01.setId(1L);

        Mockito.when(clienteReposotory.save(Mockito.any(Cliente.class)))
                .thenReturn(cliente01);

      assertDoesNotThrow(()->clienteService.inserir(cliente01));
    }

    @Test
    @DisplayName("Deve retornar todos os clientes")
    void deveRetornaTodosClientes() {

        //setUP
        Cliente primeiroCliente = new Cliente();
        primeiroCliente.setNome("Andre");
        primeiroCliente.setEndereco(new Endereco());
        primeiroCliente.setId(1L);

        Iterable<Cliente> cliente = List.of(primeiroCliente);

        Mockito.when(clienteReposotory.findAll())
                .thenReturn(cliente);

        //Execucao
        Iterable<Cliente> clientes = clienteService.buscarTodos();

       // assertiva
        assertEquals(cliente, clientes);
    }

    @Test
    @DisplayName("Deve retornar um cliente")
    void deveRetornarUmCliente() {

        //setUP
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Andre");
        cliente1.setEndereco(new Endereco());
        cliente1.setId(1L);

        Mockito.when(clienteReposotory.findById(Mockito.anyLong())).thenReturn(Optional.of(cliente1));

        //Execução
        Long id = 1L;
        Cliente cliente = clienteService.buscarPorId(id);

        //Assetiva
        assertEquals(cliente1, cliente);
    }

    @Test
    void deveAlterarUmCliente() {

        //setUP
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Andre");
        cliente1.setEndereco(new Endereco());
        cliente1.setId(1L);

        Long id = 1L;

        assertDoesNotThrow(()-> clienteService.atualizar(id, cliente1));

    }

    @Test
    void deveDeletarUmCliente() {

        Cliente cliente1 = new Cliente();
        cliente1.setNome("Andre");
        cliente1.setEndereco(new Endereco());
        cliente1.setId(1L);



        Long id=1L;
        assertDoesNotThrow(()->clienteService.deletar(id));

    }


}
package one.digitallinnovation.gof.service;


import one.digitallinnovation.gof.model.entities.Cliente;

public interface ClienteService {

        Iterable<Cliente> buscarTodos();

        Cliente buscarPorId(Long id);

        void inserir(Cliente cliente);

        void atualizar(Long id, Cliente cliente);

        void deletar(Long id);

    }

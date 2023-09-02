package one.digitallinnovation.gof.controller;

import one.digitallinnovation.gof.model.entities.Cliente;
import one.digitallinnovation.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

/*
*  TODO
*      porfavor usar uma classe intermedia para fazer o meio de campo, essa classe tem a funçao
*       de nao expor na rede algumas informaçoes da base de dados
*       utilizat um ClienteDTO
*       Aplicacao está com zero de coverage no momento, por ser legal aumentar esse taxa para 75%
* */

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        /*
        * Todo
        *  collection não foi encontrada impossibilitando
        *  o teste funcional das classe
        *  Por favor criar um collection
        *
        * */
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        /*
        *  Todo para Ok sem conteudo poderia ser legal tornar de um 200 para um 204
        *   return ResponseEntity.noContent().build();
        *   Por favor avaliar a escolha
        * */

        return ResponseEntity.ok().build();
    }
}

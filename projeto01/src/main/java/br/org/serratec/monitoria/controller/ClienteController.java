package br.org.serratec.monitoria.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.monitoria.model.Cliente;
import br.org.serratec.monitoria.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    private ResponseEntity<List<Cliente>> obterCliente() {
        List<Cliente> clientes = clienteService.obter();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<Optional<Cliente>> obterClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.obterPorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionar(@Valid @RequestBody Cliente cliente) {
        Cliente clienteAdicionado = clienteService.adicionar(cliente);
        return new ResponseEntity<>(clienteAdicionado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAlterado = clienteService.adicionar(cliente);
        return new ResponseEntity<>(clienteAlterado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

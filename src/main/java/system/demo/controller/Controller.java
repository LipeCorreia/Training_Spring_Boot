package system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.demo.entity.Cliente;
import system.demo.dto.ClienteDTO;
import system.demo.repository.Repository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente/v1")

public class Controller {

    @Autowired
    Repository repository;

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        Cliente clienteSaved = repository.save(cliente);
        return clienteSaved;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Cliente> getClienteById(@PathVariable Long id){
        Optional<Cliente> clienteReturned = repository.findById(id);
        return clienteReturned;
    }

    @DeleteMapping("/{id}")
    public void deleteCLienteById(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping
    public List<Cliente> listClientes(){
        return repository.findAll();
    }

    @PutMapping("/atualize/{id}")
    public String updateClienteById(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
        Optional<Cliente> velhoCLiente = repository.findById(id);
        if(velhoCLiente.isPresent()){
            Cliente cliente = velhoCLiente.get();
            cliente.setEndereco(clienteDTO.getEndereco());
            repository.save(cliente);
            return "Cliente de ID " + cliente.getId() + " atualizado com sucesso!!";

        }else{
           return "CLiente de ID " + id + " não existe";
        }

    }

}

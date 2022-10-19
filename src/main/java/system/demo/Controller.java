package system.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente/v1/")

public class Controller {

    @Autowired
    Repository repository;

    @PostMapping
    public  Cliente create(@RequestBody Cliente cliente){
        Cliente clienteSaved = repository.save(cliente);
        return clienteSaved;
    }


}

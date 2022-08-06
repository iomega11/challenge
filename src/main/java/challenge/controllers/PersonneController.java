package challenge.controllers;

import challenge.domain.Personne;
import challenge.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping("/personnes")
    public List<Personne> list(){
        return personneService.findAllPersonnesAlphabeticalOrder();
    }

    @PutMapping("/personnes/create")
    public Personne create(@RequestBody Personne personne){
        personneService.savePersonne(personne);
        return personne;
    }

}


package com.example.nationspring.controller;

import com.example.nationspring.exceptions.MaxPopulationException;
import com.example.nationspring.exceptions.NoIDFound;
import com.example.nationspring.exceptions.NoSuchLanguage;
import com.example.nationspring.exceptions.SameNationException;
import com.example.nationspring.model.Nation;
import com.example.nationspring.repository.NationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/nations")
public class ControlNation {

    private NationRepository nationRepository;

    public ControlNation(NationRepository nationRepository) {
        this.nationRepository = nationRepository;
    }

    @GetMapping("/allNations")
    public ResponseEntity<List<Nation>> getAllNations(){

       List<Nation>myList = this.nationRepository.findAll();

       return new ResponseEntity<>(myList, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addNation")
    public ResponseEntity<Nation> addNation(@RequestBody Nation nation){

        List<Nation> nationList = this.nationRepository.findAll();

        if(Collections.frequency(nationList,nation) == 0){
            this.nationRepository.save(nation);
            return new ResponseEntity<>(nation, HttpStatus.ACCEPTED);

        }else if(Collections.frequency(nationList,nation) > 0){

            throw new SameNationException("Natia exista deja !!!");
        }

        return null;
    }

    @GetMapping("/findNation/{id}")
    public ResponseEntity<Nation> findNation(@PathVariable long id){

       Optional<Nation> opt= this.nationRepository.findById(id);
        if(opt.isEmpty()){
            throw new NoIDFound("No ID found !!!");
        }

            return new ResponseEntity<>(opt.get(),HttpStatus.ACCEPTED);

    }

    @PutMapping("/updateTheNation")
    public ResponseEntity<Nation> updateTheNation(@RequestBody Nation nation){

        Nation nation1=nationRepository.findById(nation.getId()).get();

        nation1.setCapitalCity(nation.getCapitalCity());
        nation1.setLanguage(nation.getLanguage());
        nation1.setPopulation(nation.getPopulation());
        this.nationRepository.save(nation1);
        return  new ResponseEntity<>(nation1,HttpStatus.ACCEPTED);


    }

    @DeleteMapping("/deleteNation")
    public ResponseEntity<Nation> deleteNation(@RequestParam long id){

        Optional<Nation> opt= this.nationRepository.findById(id);
        if(opt.isEmpty()){
            throw new NoIDFound("No ID found !!!");
        }else{
            this.nationRepository.deleteById(id);
        }

        return new ResponseEntity<>(opt.get(),HttpStatus.ACCEPTED);


    }

    @GetMapping("/getAllLanguages")
    public ResponseEntity<List<String>>getAllLang(){

        List<String> languages = this.nationRepository.allMylanguages();

        return new ResponseEntity<>(languages, HttpStatus.ACCEPTED);

    }

    @GetMapping("/nationByLanguage/{language}")
    public ResponseEntity<List<Nation>> getNationByLang(@PathVariable String language){

        List<Nation> commonLanguage = this.nationRepository.nationBylanguage(language);

        if (commonLanguage.size()==0){

            throw  new NoSuchLanguage("Nu exista limba specificata !");
        }

        return new ResponseEntity<>(commonLanguage, HttpStatus.ACCEPTED);
    }

    @GetMapping("/nationByPopulation/{population}")
    public ResponseEntity<List<Nation>> getNationByPop(@PathVariable int population){

        List<Nation> byPopulation = this.nationRepository.nationByPopulation(population);

        if(byPopulation.size()==0){

            throw new MaxPopulationException("Introduceti o populatie mai mica");
        }

        return new ResponseEntity<>(byPopulation, HttpStatus.ACCEPTED);
    }





}

package pos.tech.locatech.locatech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.tech.locatech.locatech.entities.Pessoa;
import pos.tech.locatech.locatech.services.PessoaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
    private final PessoaService pessoaService;


    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(@RequestParam("page") int page,
                                                         @RequestParam("size") int size ) {
        logger.info("Acessado o endpoint de Pessoas /Pessoas");
        var pessoas = pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(@PathVariable("id") long id){
        logger.info("/Pessoas/" + id);
        var pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);

    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(@RequestBody Pessoa pessoa){
        logger.info("POST -> /Pessoas");
        pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa){
        logger.info("PUT -> /Pessoas" + id);
        pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable("id") long id){
        logger.info("DELETE -> /Pessoas" + id);
        pessoaService.deletePessoa(id);
        return ResponseEntity.ok().build();
    }
}

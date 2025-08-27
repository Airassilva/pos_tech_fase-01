package pos.tech.locatech.locatech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.tech.locatech.locatech.entities.Aluguel;
import pos.tech.locatech.locatech.services.AluguelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);
    private final AluguelService aluguelService;
    
    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(@RequestParam("page") int page,
                                                       @RequestParam("size") int size ) {
        logger.info("Acessado o endpoint de alugueis /alugueis");
        var aluguel = aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(aluguel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguel(@PathVariable("id") long id){
        logger.info("/aluguel/" + id);
        var aluguel = aluguelService.findAluguelById(id);
        return ResponseEntity.ok(aluguel);

    }

    @PostMapping
    public ResponseEntity<Void> saveAluguel(@RequestBody Aluguel aluguel){
        logger.info("POST -> /alugueis");
        aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@PathVariable("id") long id, @RequestBody Aluguel aluguel){
        logger.info("PUT -> /alugueis" + id);
        aluguelService.updateAluguel(aluguel, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable("id") long id){
        logger.info("DELETE -> /alugueis" + id);
        aluguelService.deleteAluguel(id);
        return ResponseEntity.ok().build();
    }
}

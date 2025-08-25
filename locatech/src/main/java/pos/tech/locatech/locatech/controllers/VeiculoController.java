package pos.tech.locatech.locatech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.tech.locatech.locatech.entities.Veiculo;
import pos.tech.locatech.locatech.services.VeiculoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final VeiculoService VeiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        VeiculoService = veiculoService;
    }

    //dominio/veiculos?page=1&size=10
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(@RequestParam("page") int page,
                                                         @RequestParam("size") int size ) {
        logger.info("Acessado o endpoint de veiculos /veiculos");
        var veiculos = VeiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    //dominio/veiculos/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(@PathVariable("id") long id){
        logger.info("/veiculos/" + id);
        var veiculo = VeiculoService.findVeiculoById(id);
        return ResponseEntity.ok(veiculo);

    }

    @PostMapping
    public ResponseEntity<Void> saveVeiculo(@RequestBody Veiculo veiculo){
        logger.info("POST -> /veiculos");
        VeiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@PathVariable("id") long id, @RequestBody Veiculo veiculo){
        logger.info("PUT -> /veiculos" + id);
        VeiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable("id") long id){
        logger.info("DELETE -> /veiculos" + id);
        VeiculoService.deleteVeiculo(id);
        return ResponseEntity.ok().build();
    }

}

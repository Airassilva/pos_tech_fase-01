package pos.tech.locatech.locatech.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pos.tech.locatech.locatech.dtos.AluguelRequestDTO;
import pos.tech.locatech.locatech.entities.Aluguel;
import pos.tech.locatech.locatech.repositories.AluguelRepository;
import pos.tech.locatech.locatech.repositories.VeiculoRepository;
import pos.tech.locatech.locatech.services.exceptions.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;

    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id) {
        return Optional.ofNullable(this.aluguelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado!")));
    }

    public void saveAluguel(AluguelRequestDTO DTO) {
        var aluguelEntity = calculaAluguel(DTO);
        var verifica = verificaAluguel(DTO);
        var data = verificaData(DTO);
        if (!verifica) {
            throw new ResourceNotFoundException("Veículo já alugado!");
        }
        if(!data){
            throw new IllegalArgumentException("Data final do aluguel anterior a data de início");
        }
        this.aluguelRepository.save(aluguelEntity);
    }

    public void updateAluguel(Aluguel aluguel, Long id) {
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0) {
            throw new ResourceNotFoundException("Aluguel não encontrado!");
        }
    }

    public void deleteAluguel(Long id) {
        var delete  = this.aluguelRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Aluguel não encontrado para deleção!");
        }
    }

    private Aluguel calculaAluguel(AluguelRequestDTO DTO) {
        var veiculo = veiculoRepository.findById(DTO.veiculoId())
                .orElseThrow(() -> new ResourceNotFoundException(("Veículo não encontrado!")));
        var quantidadeDias = BigDecimal.valueOf(DTO.dataFim().getDayOfYear() - DTO.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadeDias);
        return new Aluguel(DTO, valor);
    }

    private boolean verificaAluguel(AluguelRequestDTO dto) {
        var aluguel = aluguelRepository.findById(dto.veiculoId())
                .orElseThrow(() -> new ResourceNotFoundException(("Veículo não encontrado!")));
        boolean diaEscolhido = dto.dataInicio().isEqual(aluguel.getDataInicio());
        if(diaEscolhido) {
            return true;
        }
        return false;
    }

    private boolean verificaData(AluguelRequestDTO dto) {
        return dto.dataFim().isAfter(dto.dataInicio());
    }
    
}

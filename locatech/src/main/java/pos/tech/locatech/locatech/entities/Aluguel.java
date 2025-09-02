package pos.tech.locatech.locatech.entities;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import pos.tech.locatech.locatech.dtos.AluguelRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Aluguel {
    private long id;
    @NotNull
    private long pessoaId;
    @NotNull
    private long veiculoId;
    private String veiculoModelo;
    @CPF
    private String pessoaCpf;
    private String pessoaNome;
    @DateTimeFormat
    private LocalDate dataInicio;
    @DateTimeFormat
    private LocalDate dataFim;
    private BigDecimal valorTotal;

    public Aluguel(AluguelRequestDTO aluguelDTO, BigDecimal valor) {
        this.pessoaId = aluguelDTO.pessoaId();
        this.veiculoId = aluguelDTO.veiculoId();
        this.dataInicio = aluguelDTO.dataInicio();
        this.dataFim = aluguelDTO.dataFim();
        this.valorTotal = valor;
    }


}

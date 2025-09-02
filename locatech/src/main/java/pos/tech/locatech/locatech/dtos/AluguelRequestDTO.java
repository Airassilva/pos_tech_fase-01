package pos.tech.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @NotNull(message = "Deve ser selecionada uma pessoa!")
        Long pessoaId,
        @NotNull(message = "Deve ser selecionado um veículo!")
        Long veiculoId,
        @NotNull(message = "Deve ser passada a data inicial de aluguel do veículo")
        LocalDate dataInicio,
        @NotNull(message = "Deve ser passada a data final de aluguel do veículo")
        LocalDate dataFim
    ) {
}

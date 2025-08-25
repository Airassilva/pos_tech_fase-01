package pos.tech.locatech.locatech.repositories;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import pos.tech.locatech.locatech.entities.Veiculo;

import java.util.List;
import java.util.Optional;

@Repository
public class VeiculoRepositoryimp implements VeiculoRepository {

    private final JdbcClient jdbcClient;

    public VeiculoRepositoryimp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Veiculo> findById(Long id) {
        return this.jdbcClient
                .sql("select * from veiculos where :id = id")
                .param("id", id)
                .query(Veiculo.class)
                .optional();
    }

    @Override
    public List<Veiculo> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("select * from veiculos limit :size offset :offset")
                .param("size", size)
                .param("offset",offset)
                .query(Veiculo.class)
                .list();
    }

    @Override
    public Integer save(Veiculo veiculo) {
        return this.jdbcClient
                .sql("insert into veiculos (marca, modelo, placa, ano, cor, valor_diaria) values (:marca, :modelo, :placa, :ano, :cor, :valor_diaria)")
                .param("marca",veiculo.getMarca())
                .param("modelo", veiculo.getModelo())
                .param("placa", veiculo.getPlaca())
                .param("ano", veiculo.getAno())
                .param("cor", veiculo.getCor())
                .param("valor_diaria", veiculo.getValorDiaria())
                .update();
    }

    @Override
    public Integer update(Veiculo veiculo, Long id) {
        return this.jdbcClient
                .sql("update veiculos set marca = :marca, modelo = :modelo, placa = :placa, ano = :ano, cor = :cor, valor_diaria = :valor_diaria where id = :id")
                .param("id", id)
                .param("marca", veiculo.getMarca())
                .param("modelo", veiculo.getModelo())
                .param("placa", veiculo.getPlaca())
                .param("ano", veiculo.getAno())
                .param("cor", veiculo.getCor())
                .param("valor_diaria", veiculo.getValorDiaria())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("delete from veiculos where id = :id")
                .param("id", id)
                .update();
    }
}

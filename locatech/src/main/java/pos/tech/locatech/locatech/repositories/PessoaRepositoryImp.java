package pos.tech.locatech.locatech.repositories;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import pos.tech.locatech.locatech.entities.Pessoa;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryImp implements PessoaRepository {
    private final JdbcClient jdbcClient;

    public PessoaRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return this.jdbcClient
                .sql("select * from pessoas where :id = id")
                .param("id", id)
                .query(Pessoa.class)
                .optional();
    }

    @Override
    public List<Pessoa> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("select * from pessoas limit :size offset :offset")
                .param("size", size)
                .param("offset",offset)
                .query(Pessoa.class)
                .list();
    }

    @Override
    public Integer save(Pessoa Pessoa) {
        return this.jdbcClient
                .sql("insert into pessoas (nome, cpf, telefone, email) values (:nome, :cpf, :telefone, :email)")
                .param("nome", Pessoa.getNome())
                .param("cpf", Pessoa.getCpf())
                .param("telefone", Pessoa.getTelefone())
                .param("email", Pessoa.getEmail())
                .update();
    }

    @Override
    public Integer update(Pessoa Pessoa, Long id) {
        return this.jdbcClient
                .sql("update pessoas set nome = :nome, cpf = :cpf, telefone = :telefone, email = :email where id = :id")
                .param("id", id)
                .param("nome", Pessoa.getNome())
                .param("cpf", Pessoa.getCpf())
                .param("telefone", Pessoa.getTelefone())
                .param("email", Pessoa.getEmail())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("delete from pessoas where id = :id")
                .param("id", id)
                .update();
    }
}

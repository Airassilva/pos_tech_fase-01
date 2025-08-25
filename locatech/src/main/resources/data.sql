create table veiculos(
    id bigint auto_increment primary key,
    marca varchar(255),
    modelo varchar(255),
    placa varchar(255),
    ano int,
    cor varchar(20),
    valor_diaria decimal(10,2)
);

create table pessoas(
    id bigint auto_increment primary key,
    nome varchar(255),
    cpf varchar(11),
    telefone varchar(11),
    email varchar(255)
);

insert into veiculos (marca, modelo, placa, ano, cor, valor_diaria)
values ('Chevrolet', 'Celta', 'abc1234',2010,'preto', 100.00);

insert into pessoas (nome, cpf, telefone, email)
values ('Alana', '123456789', '81988888888','sla@gmail.com');
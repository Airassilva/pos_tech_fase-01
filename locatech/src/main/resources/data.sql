create table veiculos(
    id bigint auto_increment primary key,
    marca varchar(255),
    modelo varchar(255),
    placa varchar(255),
    ano int,
    cor varchar(20),
    valor_diararia decimal(10,2)
);

insert into veiculos (marca, modelo, placa, ano, cor, valor_diararia)
values ('Chevrolet', 'Celta', 'abc1234',2010,'preto', 100.00);
CREATE TABLE agendamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cliente VARCHAR(255),
    data VARCHAR(255),
    hora VARCHAR(255),
    servico VARCHAR(255),
    PRIMARY KEY (id)
);
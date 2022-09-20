CREATE TABLE adresses (
        id     varchar(80) primary key,
        hash   varchar(80), 
        balance float
);


CREATE TABLE blocks (
        id     varchar(80) primary key,
        number   serial, 
        time time,
        size float
);



CREATE TABLE blockchains (
        id     varchar(80) primary key,
        name   varchar(80), 
        id_adress_main_coin varchar(80) references adresses(id),
        marketcap float
);



CREATE TABLE transactions (
        id     varchar(80) primary key,
        hash   varchar(80), 
        status   varchar(80), 
        id_block varchar(80) references blocks(id),
        time time,
        id_from varchar(80) references adresses(id),
        id_to varchar(80) references adresses(id),
        value float,
        fee float
);



CREATE TABLE adresses_transations (
        id_address     varchar(80), 
        id_transaction   varchar(80), 
        primary key (id_address, id_transaction)
);

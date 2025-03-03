CREATE TABLE Cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    cognome VARCHAR(50),
    data_nascita DATE,
    indirizzo VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(50),
    codice_fiscale VARCHAR(16),
    bersani BOOLEAN
);

CREATE TABLE Bene (
    id_bene SERIAL PRIMARY KEY,
    tipo VARCHAR(50),
    identificativo VARCHAR(50),
    descrizione TEXT
);

CREATE TABLE Polizza (
    id_polizza SERIAL PRIMARY KEY,
    data_inizio DATE,
    data_scadenza DATE,
    tipo VARCHAR(50),
    importo_rata NUMERIC(10,2),
    importo NUMERIC(10,2),
    premio NUMERIC(10,2),
    stato VARCHAR(50),
    id_cliente INTEGER,
    id_bene INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_bene) REFERENCES bene(id_bene)
);

CREATE TABLE Pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    id_polizza INTEGER,
    data_pagamento DATE,
    importo NUMERIC(10,2),
    metodo VARCHAR(50),
    causale VARCHAR(100),
    FOREIGN KEY (id_polizza) REFERENCES polizza(id_polizza)
);

CREATE TABLE Sinistro (
    id_sinistro SERIAL PRIMARY KEY,
    id_polizza INTEGER,
    data DATE,
    descrizione TEXT,
    stato VARCHAR(50),
    valore_danno NUMERIC(10,2),
    importo_concesso NUMERIC(10,2),
    FOREIGN KEY (id_polizza) REFERENCES polizza(id_polizza)
);

CREATE TABLE Reclamo (
    id_reclamo SERIAL PRIMARY KEY,
    id_polizza INTEGER,
    data DATE,
    motivo TEXT,
    stato VARCHAR(50),
    FOREIGN KEY (id_polizza) REFERENCES polizza(id_polizza)
);

CREATE TABLE Precedente (
    id_precedente SERIAL PRIMARY KEY,
    id_cliente INTEGER,
    tipo VARCHAR(50),
    pena VARCHAR(50),
    scontata BOOLEAN,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- Creazione della tabella Cliente con vincolo di unicità su codice_fiscale
CREATE TABLE Cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    cognome VARCHAR(50),
    data_nascita DATE,
    indirizzo VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(50),
    codice_fiscale VARCHAR(16) UNIQUE, -- Unicità per il codice fiscale
    bersani BOOLEAN
);

-- Creazione della tabella Bene
CREATE TABLE Bene (
    id_bene SERIAL PRIMARY KEY,
    tipo VARCHAR(50),
    identificativo VARCHAR(50),
    descrizione TEXT
);

-- Creazione della tabella Polizza con controlli su importo
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
    FOREIGN KEY (id_bene) REFERENCES bene(id_bene),
    CHECK (importo_rata > 0 AND importo > 0 AND premio > 0) -- Controllo che importo, importo_rata e premio siano positivi
);

-- Creazione della tabella Pagamento con vincolo di unicità su id_polizza e data_pagamento
CREATE TABLE Pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    id_polizza INTEGER,
    data_pagamento DATE,
    importo NUMERIC(10,2),
    metodo VARCHAR(50),
    causale VARCHAR(100),
    FOREIGN KEY (id_polizza) REFERENCES polizza(id_polizza),
    CONSTRAINT unique_payment UNIQUE (id_polizza, data_pagamento) -- Unicità per evitare pagamenti duplicati
);

-- Creazione della tabella Sinistro con controlli su valore_danno e importo_concesso
CREATE TABLE Sinistro (
    id_sinistro SERIAL PRIMARY KEY,
    id_polizza INTEGER,
    data DATE,
    descrizione TEXT,
    stato VARCHAR(50),
    valore_danno NUMERIC(10,2),
    importo_concesso NUMERIC(10,2),
    FOREIGN KEY (id_polizza) REFERENCES polizza(id_polizza),
    CHECK (valore_danno >= 0 AND importo_concesso >= 0) -- Controllo che valore_danno e importo_concesso siano positivi
);

-- Creazione della tabella Reclamo
CREATE TABLE Reclamo (
    id_reclamo SERIAL PRIMARY KEY,
    id_polizza INTEGER,
    data DATE,
    motivo TEXT,
    stato VARCHAR(50),
    FOREIGN KEY (id_polizza) REFERENCES polizza(id_polizza)
);

-- Creazione della tabella Precedente con vincolo di unicità su id_cliente e tipo
CREATE TABLE Precedente (
    id_precedente SERIAL PRIMARY KEY,
    id_cliente INTEGER,
    tipo VARCHAR(50),
    pena VARCHAR(50),
    scontata BOOLEAN,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    CONSTRAINT unique_precedente UNIQUE (id_cliente, tipo) -- Unicità per evitare più precedenti dello stesso tipo per lo stesso cliente
);

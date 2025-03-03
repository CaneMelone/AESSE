-- Inserimento clienti
INSERT INTO Cliente (id_cliente, nome, cognome, data_nascita, indirizzo, telefono, email, codice_fiscale, bersani) VALUES
(1, 'Mario', 'Rossi', '1980-01-15', 'Via Roma 1, Milano', '0212345678', 'mario.rossi@example.com', 'RSSMRA80A01F205X', false),
(2, 'Luigi', 'Verdi', '1975-05-20', 'Via Verdi 10, Torino', '0119876543', 'luigi.verdi@example.com', 'VRDLGU75E20T089K', false),
(3, 'Anna', 'Bianchi', '1990-03-12', 'Corso Italia 5, Napoli', '0812345678', 'anna.bianchi@example.com', 'BNCHNN90C12F839L', true),
(4, 'Marco', 'Neri', '1985-07-08', 'Via Dante 15, Firenze', '0551234567', 'marco.neri@example.com', 'NRIMRC85L08D612P', false),
(5, 'Sara', 'Russo', '1992-11-30', 'Piazza Garibaldi 3, Bologna', '0512349876', 'sara.russo@example.com', 'RSSSRA92S30A662R', false),
(6, 'Paolo', 'Ferrari', '1978-04-22', 'Via Milano 20, Genova', '0109876543', 'paolo.ferrari@example.com', 'FRRPLA78D22G273S', true),
(7, 'Laura', 'Romano', '1988-09-17', 'Via Libertà 8, Palermo', '0912345678', 'laura.romano@example.com', 'RMNLRA88P17Z404Q', false),
(8, 'Giulia', 'Moretti', '1995-12-05', 'Viale Europa 12, Roma', '0654321987', 'giulia.moretti@example.com', 'MRTGLI95T05H501U', true),
(9, 'Alessandro', 'De Luca', '1982-06-30', 'Via Mazzini 22, Bari', '0801234567', 'alessandro.deluca@example.com', 'DLCALE82H30B354X', false),
(10, 'Francesca', 'Greco', '1990-10-10', 'Corso Vittorio 7, Verona', '0456781234', 'francesca.greco@example.com', 'GRCFNC90R10L736Y', true);

-- Inserimento beni (asset di vario tipo)
INSERT INTO Bene (id_bene, tipo, identificativo, descrizione) VALUES
(1, 'Auto', 'AB123CD', 'Fiat Panda 2010'),
(2, 'Casa', 'Casa001', 'Appartamento in centro'),
(3, 'Moto', 'MOTO99', 'Yamaha MT-07'),
(4, 'Barca', 'BARC01', 'Piccola barca a vela'),
(5, 'Auto', 'XY987ZT', 'BMW Serie 3 2018'),
(6, 'Casa', 'Casa002', 'Villa in campagna'),
(7, 'Moto', 'MOTO88', 'Ducati Monster'),
(8, 'Auto', 'LM654OP', 'Audi A3 2020');

-- Inserimento polizze
-- Nota: importo_rata = importo - premio
INSERT INTO Polizza (id_polizza, data_inizio, data_scadenza, tipo, importo_rata, importo, premio, stato, id_cliente, id_bene) VALUES
(1, '2023-01-01', '2024-01-01', 'RC Auto', 500, 1500, 1000, 'Attiva', 1, 1),
(2, '2023-02-15', '2024-02-15', 'Polizza Casa', 300, 1300, 1000, 'Attiva', 2, 2),
(3, '2022-06-01', '2023-06-01', 'RC Moto', 200, 700, 500, 'Scaduta', 3, 3),
(4, '2023-03-10', '2024-03-10', 'Polizza Barca', 400, 1400, 1000, 'Attiva', 4, 4),
(5, '2023-04-20', '2024-04-20', 'RC Auto', 600, 1600, 1000, 'Attiva', 5, 5),
(6, '2022-09-01', '2023-09-01', 'Polizza Casa', 350, 1350, 1000, 'Scaduta', 6, 6),
(7, '2023-05-15', '2024-05-15', 'RC Moto', 250, 750, 500, 'Attiva', 7, 7),
(8, '2023-07-01', '2024-07-01', 'RC Auto', 550, 1550, 1000, 'Attiva', 8, 8),
(9, '2022-11-11', '2023-11-11', 'RC Auto', 500, 1500, 1000, 'Scaduta', 9, 1),
(10, '2023-08-08', '2024-08-08', 'Polizza Casa', 320, 1320, 1000, 'Attiva', 10, 2),
(11, '2023-09-15', '2024-09-15', 'RC Moto', 180, 680, 500, 'Attiva', 1, 3),
(12, '2023-10-01', '2024-10-01', 'RC Auto', 600, 1600, 1000, 'Attiva', 2, 5);

-- Inserimento pagamenti (almeno uno per ogni polizza)
INSERT INTO Pagamento (id_pagamento, id_polizza, data_pagamento, importo, metodo, causale) VALUES
(1, 1, '2023-01-05', 500, 'Bonifico', 'Prima rata'),
(2, 2, '2023-02-20', 300, 'Carta di Credito', 'Prima rata'),
(3, 3, '2022-06-05', 200, 'Bonifico', 'Prima rata'),
(4, 4, '2023-03-15', 400, 'Contanti', 'Prima rata'),
(5, 5, '2023-04-25', 600, 'Bonifico', 'Prima rata'),
(6, 6, '2022-09-05', 350, 'Carta di Credito', 'Prima rata'),
(7, 7, '2023-05-20', 250, 'Bonifico', 'Prima rata'),
(8, 8, '2023-07-05', 550, 'Contanti', 'Prima rata'),
(9, 9, '2022-11-15', 500, 'Bonifico', 'Prima rata'),
(10, 10, '2023-08-10', 320, 'Carta di Credito', 'Prima rata'),
(11, 11, '2023-09-20', 180, 'Contanti', 'Prima rata'),
(12, 12, '2023-10-05', 600, 'Bonifico', 'Prima rata');

-- Inserimento sinistri (alcune polizze hanno sinistro)
INSERT INTO Sinistro (id_sinistro, id_polizza, data, descrizione, stato, valore_danno, importo_concesso) VALUES
(1, 1, '2023-02-01', 'Incidente minore', 'Aperto', 1200, 800),
(2, 3, '2022-07-10', 'Caduta dalla moto', 'Chiuso', 800, 500),
(3, 5, '2023-05-05', 'Furto auto', 'Aperto', 2000, 1500),
(4, 7, '2023-06-15', 'Danneggiamento moto', 'Chiuso', 700, 400);

-- Inserimento reclami (alcune polizze hanno reclamo)
INSERT INTO Seclamo (id_reclamo, id_polizza, data, motivo, stato) VALUES
(1, 1, '2023-02-05', 'Ritardo nella liquidazione', 'In corso'),
(2, 3, '2022-07-15', 'Danno ingiustificato', 'Accettato'),
(3, 5, '2023-05-10', 'Richiesta indennizzo non riconosciuta', 'Respinto'),
(4, 7, '2023-06-20', 'Errore di valutazione', 'In corso');

-- Inserimento precedenti (solo per alcuni clienti)
INSERT INTO Precedente (id_precedente, id_cliente, tipo, pena, scontata) VALUES
(1, 1, 'Violazione', 'Multe', false),
(2, 4, 'Infrazione', 'Sospensione', true),
(3, 9, 'Furto', 'Detenzione', false);


select * from Polizza
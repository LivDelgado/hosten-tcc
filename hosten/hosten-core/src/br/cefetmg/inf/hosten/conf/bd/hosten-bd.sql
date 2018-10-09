-- Query on database 'Hosten'

-- Create tables section ----------------------------------------------------------------
--

-- Table Programa
CREATE TABLE IF NOT EXISTS Programa(
 id Serial NOT NULL,
 codPrograma Character(3) NOT NULL,
 desPrograma Character varying(200) NOT NULL,
 PRIMARY KEY(id)
);

-- Table Cargo
CREATE TABLE IF NOT EXISTS Cargo(
 id Serial NOT NULL,
 codCargo Character(3) NOT NULL,
 nomCargo Character varying(40) NOT NULL,
 idtMaster Boolean NOT NULL,
 PRIMARY KEY(id)
);

-- Table CargoPrograma
CREATE TABLE IF NOT EXISTS CargoPrograma(
 id Serial NOT NULL,
 idPrograma Integer NOT NULL,
 idCargo Integer NOT NULL,
 PRIMARY KEY(id)
);

-- Table Usuario
CREATE TABLE IF NOT EXISTS Usuario(
 id Serial NOT NULL,
 codUsuario Character(4) NOT NULL,
 nomUsuario Character varying(90) NOT NULL,
 idCargo Integer NOT NULL,
 desSenha Character(64),
 desEmail Character varying(60),
 PRIMARY KEY(id)
);

-- Table Quarto
CREATE TABLE IF NOT EXISTS Quarto(
 id Serial NOT NULL,
 nroQuarto Smallint NOT NULL,
 idCategoria Integer NOT NULL,
 idtOcupado Boolean,
 PRIMARY KEY(id)
);
-- Create indexes for table Quarto
CREATE INDEX IX_Relationship1 ON Quarto (idCategoria);


-- Table Categoria
CREATE TABLE IF NOT EXISTS Categoria(
 id Serial NOT NULL,
 codCategoria Character(3) NOT NULL,
 nomCategoria Character varying(40) NOT NULL,
 vlrDiaria Numeric(7,2) NOT NULL,
 PRIMARY KEY(id)
);

-- Table ItemConforto
CREATE TABLE IF NOT EXISTS ItemConforto(
 id Serial NOT NULL,
 codItem Character(3) NOT NULL,
 desItem Character varying(40) NOT NULL,
 PRIMARY KEY(id)
);

-- Table CategoriaItemConforto
CREATE TABLE IF NOT EXISTS CategoriaItemConforto(
 id Serial NOT NULL,
 idCategoria Integer NOT NULL,
 idItem Integer NOT NULL,
 PRIMARY KEY(id)
);

-- Table Hospede
CREATE TABLE IF NOT EXISTS Hospede(
 id Serial NOT NULL,
 codCPF Character(14) NOT NULL,
 nomHospede Character varying(90) NOT NULL,
 desTelefone Character varying(14) NOT NULL,
 desEmail Character varying(90) NOT NULL,
 PRIMARY KEY(id)
);

-- Table Hospedagem
CREATE TABLE IF NOT EXISTS Hospedagem(
 id Serial NOT NULL,
 datCheckIn Timestamp NOT NULL,
 datCheckOut Timestamp,
 vlrPago Numeric(7,2),
 idHospede Integer,
 PRIMARY KEY(id)
);
-- Create indexes for table Hospedagem
CREATE INDEX IX_Relationship2 ON Hospedagem (idHospede);

-- Table Servico
CREATE TABLE IF NOT EXISTS Servico(
 id Serial NOT NULL,
 desServico Character varying(40) NOT NULL,
 vlrUnit Numeric(7,2) NOT NULL,
 idServicoArea Integer NOT NULL,
 PRIMARY KEY(id)
);
-- Create indexes for table Servico
CREATE INDEX IX_Relationship3 ON Servico (idServicoArea);


-- Table ServicoArea
CREATE TABLE IF NOT EXISTS ServicoArea(
 id Serial NOT NULL,
 codServicoArea Character(3) NOT NULL,
 nomServicoArea Character varying(40) NOT NULL,
 PRIMARY KEY(id)
);

-- Table QuartoConsumo
CREATE TABLE IF NOT EXISTS QuartoConsumo(
 id Serial NOT NULL,
 idQuartoHospedagem Integer NOT NULL,
 datConsumo Timestamp NOT NULL,
 qtdConsumo Smallint NOT NULL,
 idServico Integer NOT NULL,
 idUsuarioRegistro Integer,
 PRIMARY KEY(id)
);
-- Create indexes for table QuartoConsumo
CREATE INDEX IX_Relationship4 ON QuartoConsumo (idServico);
CREATE INDEX IX_Relationship5 ON QuartoConsumo (idUsuarioRegistro);

-- Table QuartoHospedagem
CREATE TABLE IF NOT EXISTS QuartoHospedagem(
 id Serial NOT NULL,
 idHospedagem Integer NOT NULL,
 idQuarto Integer NOT NULL,
 nroAdultos Smallint NOT NULL,
 nroCriancas Smallint NOT NULL,
 vlrDiaria Numeric(7,2) NOT NULL,
 PRIMARY KEY(id)
);

--
-- Create tables section ----------------------------------------------------------------

-- Create foreign keys (relationships) section ----------------------------------------------------------------
--

-- Cargo Programa
ALTER TABLE CargoPrograma ADD CONSTRAINT Relationship1 
    FOREIGN KEY (idPrograma) 
    REFERENCES Programa (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE CargoPrograma ADD CONSTRAINT Relationship2 
    FOREIGN KEY (idCargo) 
    REFERENCES Cargo (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

-- Usuario
ALTER TABLE Usuario ADD CONSTRAINT Relationship3 
    FOREIGN KEY (idCargo) 
    REFERENCES Cargo (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

-- Categoria Item Conforto
ALTER TABLE CategoriaItemConforto ADD CONSTRAINT Relationship4 
    FOREIGN KEY (idCategoria) 
    REFERENCES Categoria (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE CategoriaItemConforto ADD CONSTRAINT Relationship5 
    FOREIGN KEY (idItem) 
    REFERENCES ItemConforto (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

-- Quarto
ALTER TABLE Quarto ADD CONSTRAINT Relationship6 
    FOREIGN KEY (idCategoria) 
    REFERENCES Categoria (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

-- Quarto Consumo
ALTER TABLE QuartoConsumo ADD CONSTRAINT Relationship7 
    FOREIGN KEY (idServico)    
    REFERENCES Servico (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE QuartoConsumo ADD CONSTRAINT Relationship8 
    FOREIGN KEY (idUsuarioRegistro) 
    REFERENCES Usuario (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE QuartoConsumo ADD CONSTRAINT Relationship9 
    FOREIGN KEY (idQuartoHospedagem) 
    REFERENCES QuartoHospedagem (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

-- Servico
ALTER TABLE Servico ADD CONSTRAINT Relationship10 
    FOREIGN KEY (idServicoArea) 
    REFERENCES ServicoArea (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

-- Quarto Hospedagem
ALTER TABLE QuartoHospedagem ADD CONSTRAINT Relationship11 
    FOREIGN KEY (idHospedagem) 
    REFERENCES Hospedagem (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE QuartoHospedagem ADD CONSTRAINT Relationship12 
    FOREIGN KEY (idQuarto) 
    REFERENCES Quarto (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

-- Hospedagem
ALTER TABLE Hospedagem ADD CONSTRAINT Relationship13 
    FOREIGN KEY (idHospede) 
    REFERENCES Hospede (id) 
    
    ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Create foreign keys (relationships) section ----------------------------------------------------------------

-- Create views section ----------------------------------------------------------------
--

-- View RelatorioDespesas 
CREATE OR REPLACE VIEW RelatorioDespesas AS
SELECT 
    Row_number() OVER (ORDER BY 1 ASC) AS id, 
    A.idHospedagem, 
    A.idQuarto, 
    A.nroAdultos, 
    A.nroCriancas, 
    A.vlrDiaria,
    B.datCheckIn, 
    B.datCheckOut, 
    B.vlrPago,
    C.nomHospede,
    D.idServico, 
    D.qtdConsumo,
    E.desServico, 
    E.vlrUnit
FROM
	QuartoHospedagem A
	JOIN Hospedagem B 
        ON A.idHospedagem = B.id
	JOIN Hospede C 
        ON B.idHospede = C.id
	JOIN QuartoConsumo D 
        ON A.id = D.idQuartoHospedagem
	JOIN Servico E 
        ON D.idServico = E.id;

-- View Quarto Estado
CREATE OR REPLACE VIEW QuartoEstado AS
SELECT 
    Row_number() OVER (ORDER BY 1 ASC) AS id,
    A.idQuarto,
    A.nroAdultos,
    A.nroCriancas,
    D.vlrDiaria,
    C.idtOcupado,
    B.datCheckOut
FROM
    QuartoHospedagem A
    JOIN Hospedagem B
        ON A.idHospedagem = B.id
    JOIN Quarto C
        ON A.idQuarto = C.id
    JOIN Categoria D
        ON C.idCategoria = D.id;

--
-- Create views section ----------------------------------------------------------------

-- Insert section ----------------------------------------------------------------
--

-- Insert on Programa
INSERT INTO public.programa(codprograma, desprograma)
    VALUES ('001', 'cargos'),
        ('002', 'check-in'),
        ('003', 'check-out'),
        ('004', 'detalhes-conta'),
        ('005', 'lancamento-despesas'),
        ('006', 'hospedes'),
        ('007', 'funcionarios'),
        ('008', 'itens-conforto'),
        ('009', 'categoria-quarto'),
        ('010', 'quartos'),
        ('011', 'servico-area'),
        ('012', 'servicos'),
        ('013', 'estado-quarto');

-- Insert on Cargo
INSERT INTO public.cargo(codcargo, nomcargo, idtmaster)
    VALUES('001', 'Administrador', true),
        ('002', 'Zé ninguém', false);

-- Insert on CargoPrograma 
INSERT INTO public.cargoprograma(idprograma, idcargo)
    VALUES ('008', '002'),
        ('009', '002');

-- Insert on Usuario
INSERT INTO public.usuario(codusuario, nomusuario, idcargo, dessenha, desemail)
    VALUES('0001', 'O Cara', '001','B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'adm@email.com'),
        ('0002', 'Zé', '002','B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'ze@email.com');

--
-- Insert section ----------------------------------------------------------------
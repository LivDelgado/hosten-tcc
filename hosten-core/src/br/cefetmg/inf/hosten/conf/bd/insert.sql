SELECT truncate_tables();
--
-- INSERÇÕES
--
-- INSERÇÃO EM CARGO
--character, character, boolean
	INSERT INTO 
		public.cargo(
			codcargo, 
			nomcargo, 
			idtmaster
		)
		VALUES 
			('001', 'Administrador', true),
			('002', 'Recepcionista', false),
			('003', 'Gerente de RH', false),
			('004', 'Coordenador de Serviços', false),
			('005', 'Chefe de Governança', false);
--
-- INSERÇÃO EM CARGO PROGRAMA
-- character, character
	INSERT INTO 
		public.cargoprograma(
			codcargo,
			codprograma
		)
		-- recepcionista
		VALUES 
			('002', '002'),
			('002', '003'),
			('002', '004'),
			('002', '005'),
			('002', '013'),
		-- gerente de rh
			('003', '001'),
			('003', '007'),
		-- coordenador de serviços
			('004', '011'),
			('004', '012'),
		-- chefe de governança
			('005', '008'),
			('005', '009'),
			('005', '010');
--
-- INSERÇÃO EM USUÁRIO (FUNCIONÁRIOS DO HOTEL)
-- character, character, character, character, character
	INSERT INTO 
	public.usuario(
		codusuario,
		nomusuario, 
		codcargo, 
		dessenha, 
		desemail
	)
	VALUES 
	-- administradores
		('0001', 'Emanuela Amorim Jorge', '001', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'manu@hosten.com'),
		('0002', 'Joice Sena de Camargos', '001', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'joice@hosten.com'),
		('0003', 'Lívia Delgado de Almeida Carneiro', '001', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'livia@hosten.com'),
		('0004', 'Nícolas Catarina Parreiras', '001', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'nick@hosten.com'),
		('0005', 'Cristiano Amaral Maffort', '001', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'maffort@hosten.com'),
		('0006', 'Edson Marchetti da Silva', '001', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'edson@hosten.com'),
		('0007', 'Glívia Angélica Rodrigues Barbosa', '001', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'glivia@hosten.com'),
	-- recepcionistas
		('0011', 'Carlos Eduardo Isaac Monteiro', '002', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'carlos@hosten.com'),
		('0012', 'Lorena Carolina Assunção', '002', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'lorena@hosten.com'),
		('0013', 'Agatha Aparecida Ribeiro', '002', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'agatha@email.com'),
		('0014', 'Caio Ferreira Santos', '002', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'caio@hosten.com'),
		('0015', 'Mateus Costa Santos', '002', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'mateus@hosten.com'),
	-- gerentes de rh
		('0021', 'Lucas Correia Rodrigues', '003', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'lucas@hosten.com'),
		('0022', 'Renan Rocha Castro', '003', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'renan@hosten.com'),
		('0023', 'Laura Souza Goncalves', '003', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'laura@hosten.com'),
		('0024', 'Gabriela Martins Alves', '003', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'gabriela@hosten.com'),
		('0025', 'Victor Dias Costa', '003', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'victor@hosten.com'),
	-- coordenadores de serviços
		('0031', 'Vitória Dias Correia', '004', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'vitoria@hosten.com'),
		('0032', 'Leila Araujo Rocha', '004', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'leila@hosten.com'),
		('0033', 'Kaua Barros Cavalcanti', '004', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'kaua@hosten.com'),
		('0034', 'Danilo Santos Barbosa', '004', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'danilo@hosten.com'),
		('0035', 'Thaís Barbosa Cardoso', '004', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'thais@hosten.com'),
	-- chefes de governança
		('0041', 'Eduarda Almeida Rocha', '005', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'eduarda@hosten.com'),
		('0042', 'Samuel Cavalcanti Almeida', '005', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'samuel@hosten.com'),
		('0043', 'Leonor Alves Rodrigues', '005', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'leonor@hosten.com'),
		('0044', 'Emily Martins Cardoso', '005', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'emily@hosten.com'),
		('0045', 'Luiza Pereira Castro', '005', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', 'luiza@hosten.com');
--
-- INSERÇÃO EM HÓSPEDE
-- character, character, character, character
	INSERT INTO 
	public.hospede(
            codcpf,
			nomhospede,
			destelefone,
			desemail
	)
    VALUES 
		('745.202.440-05', 'Taylor Alison Swift', '(021)2555-0117', 'taylorswift@hosp.com'),
		('337.187.120-75', 'Shawn Peter Raul Mendes', '(071)3694-6068', 'shawnmendes@hosp.com'),
		('708.145.550-63', '‎Matthew Timothy Healy', '(084)2528-8820', 'mattyhealy@hosp.com'),
		('156.788.500-40', 'Ella Marija Lani', '(061)3993-9636', 'lorde@hosp.com'),
		('295.238.360-05', 'Sherlock Holmes', '(041)2927-4000', 'sherlock@hosp.com'),
		('800.068.650-38', 'Rei Leônidas I de Esparta', '(082)3871-6488', 'leonidas@hosp.com'),
		('279.310.900-20', 'Elizabeth Woolridge Grant', '(051)2641-4587', 'lanadelrey@hosp.com'),
		('500.948.100-60', 'Florence Leontine Mary Welch', '(071)2678-8745', 'florencewelch@hosp.com'),
		('874.544.480-38', 'Jesse James Rutherford', '(031)3988-2406', 'jesserutherford@hosp.com'),
		('027.192.200-19', 'Brendon Boyd Urie', '(011)2603-8339', 'brendonurie@hosp.com');
--
-- INSERÇÃO EM ITEM DE CONFORTO
-- character, character
	INSERT INTO 
		public.itemconforto(
			coditem,
			desitem
		)
		VALUES 
			('001', 'Smart TV LED Full HD'),
			('002', 'Smart TV LED Ultra HD 4K Tela Curva'),
			('003', 'TV LCD'),
			('004', 'TV Plasma'),
			('005', 'Cama Solteiro King Size'),
			('006', 'Cama Solteiro Queen Size'),
			('007', 'Sofá cama'),
			('008', 'Cama com Dossel'),
			('009', 'Cama Casal King Size'),
			('010', 'Cama Casal Queen Size'),
			('011', 'Mesa de Trabalho'),
			('012', 'Beliche'),
			('013', 'Beliche com Gaveteiro'),
			('014', 'Cama Solteiro'),
			('015', 'Criado-mudo'),
			('016', 'Armário'),
			('017', 'Escrivaninha'),
			('018', 'Telefone'),
			('019', 'Rádio'),
			('020', 'Cama Casal');
--
-- INSERÇÃO EM CATEGORIA DE QUARTO
-- character, character, numeric(7,2)
	INSERT INTO 
		public.categoria(
            codcategoria, 
			nomcategoria, 
			vlrdiaria
	)
    VALUES 
		('001', 'Quarto Solteiro Standard', 100.00),
		('002', 'Quarto Solteiro Executivo', 150.00),
		('003', 'Quarto Solteiro Deluxe', 200.00),
		('004', 'Quarto Duplo Solteiro Standard', 200.00),
		('005', 'Quarto Duplo Solteiro Executivo', 250.00),
		('006', 'Quarto Duplo Solteiro Deluxe', 300.00),
		('007', 'Quarto Casal Standard', 300.00),
		('008', 'Quarto Casal Executivo', 400.00),
		('009', 'Quarto Casal Deluxe', 500.00),
		('010', 'Dormitório', 200.00);
--
-- INSERÇÃO EM CATEGORIAITEM
-- character, character
	INSERT INTO 
		public.categoriaitemconforto(
            codcategoria,
			coditem
		)
    VALUES 
	-- quarto solteiro standard
		('001', '003'), ('001', '014'), ('001', '015'), ('001', '016'), ('001', '018'),
	-- quarto solteiro executivo
		('002', '001'), ('002', '008'), ('002', '015'), ('002', '016'), ('002', '017'), ('002', '018'), ('002', '019'),
	-- quarto solteiro deluxe
		('003', '002'), ('003', '005'), ('003', '011'), ('003', '015'), ('003', '016'), ('003', '017'), ('003', '018'), ('003', '019'),
	-- quarto duplo solteiro standard
		('004', '003'), ('004', '014'), ('004', '015'), ('004', '016'), ('004', '018'),
	-- quarto duplo solteiro executivo
		('005', '001'), ('005', '008'), ('005', '015'), ('005', '016'), ('005', '017'), ('005', '018'), ('005', '019'),
	-- quarto duplo solteiro deluxe
		('006', '002'), ('006', '005'), ('006', '011'), ('006', '015'), ('006', '016'), ('006', '017'), ('006', '018'), ('006', '019'),
	-- quarto casal standard
		('007', '003'), ('007', '020'), ('007', '015'), ('007', '016'), ('007', '018'),
	-- quarto casal executivo
		('008', '001'), ('008', '010'), ('008', '015'), ('008', '016'), ('008', '017'), ('008', '018'), ('008', '019'),
	-- quarto casal deluxe
		('009', '002'), ('009', '009'), ('009', '011'), ('009', '015'), ('009', '016'), ('009', '017'), ('009', '018'), ('009', '019'),
	-- dormitório
		('010', '004'), ('010', '007'), ('010', '011'), ('010', '013'), ('010', '015'), ('010', '016'), ('010', '017'), ('010', '018');
--
-- INSERÇÃO EM QUARTO
-- smallint, character, boolean
	INSERT INTO 
		public.quarto(
            nroquarto,
			codcategoria,
			idtocupado
		)
    VALUES 
		(101, '001', false),
		(102, '001', false),
		(103, '001', false),
		(104, '001', false),
		(105, '001', false),
		(201, '002', false),
		(202, '002', false),
		(203, '002', false),
		(204, '002', false),
		(205, '002', false),
		(301, '003', false),
		(302, '003', false),
		(303, '003', false),
		(304, '003', false),
		(305, '003', false),
		(401, '004', false),
		(402, '004', false),
		(403, '004', false),
		(404, '004', false),
		(405, '004', false),
		(501, '005', false),
		(502, '005', false),
		(503, '005', false),
		(504, '005', false),
		(505, '005', false),
		(601, '006', false),
		(602, '006', false),
		(603, '006', false),
		(604, '006', false),
		(605, '006', false),
		(701, '007', false),
		(702, '007', false),
		(703, '007', false),
		(704, '007', false),
		(705, '007', false),
		(801, '008', false),
		(802, '008', false),
		(803, '008', false),
		(804, '008', false),
		(805, '008', false),
		(901, '009', false),
		(902, '009', false),
		(903, '009', false),
		(904, '009', false),
		(905, '009', false),
		(911, '010', false),
		(912, '010', false),
		(913, '010', false),
		(914, '010', false),
		(915, '010', false);
--
-- INSERÇÃO EM ÁREA DE SERVIÇO
-- character, character
	INSERT INTO 
		public.servicoarea(
			codservicoarea,
			nomservicoarea
		)
	VALUES 
		('001', 'Restaurante'),
		('002', 'Spa'),
		('003', 'Lazer'),
		('004', 'Salão'),
		('005', 'Estacionamento');
--
-- INSERÇÃO EM SERVIÇO
-- smallint, character, numeric(7,2), character
	INSERT INTO 
		public.servico(
			desservico, 
			vlrunit, 
			codservicoarea
		)
    VALUES
	-- restaurante
		('Café da manhã', 7.00, '001'),
		('Almoço', 10.00, '001'),
		('Jantar', 10.00, '001'),
	-- spa
		('Piscina climatizada', 10.00, '002'),
		('Sauna', 20.00, '002'),
		('Massagem', 20.00, '002'),
		('Hidromassagem', 25.00, '002'),
		('Hidratação à base de cacau', 50.00, '002'),
	-- lazer
		('Academia (dia)', 40.00, '003'),
		('Piscina (dia)', 20.00, '003'),
		('Ginásio (dia)', 50.00, '003'),
		('Equitação (hora)', 80.00, '003'),
		('Pista de kart (hora)', 60.00, '003'),
	-- salão
		('Matização dos fios', 50.00, '004'),
		('Sobrancelha', 20.00, '004'),
		('Spa de mãos', 40.00, '004'),
		('Spá de pés', 45.00, '004'),
		('Maquiagem', 50.00, '004'),
	-- estacionamento
		('1 vaga', 5.00, '005'),
		('Estacionamento com manobrista', 15.00, '005');
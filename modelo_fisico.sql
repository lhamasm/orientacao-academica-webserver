CREATE DATABASE oa_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE oa_db;

CREATE TABLE USUARIO
(
	matricula char(9) not null,
	nome varchar(45),
	sobrenome varchar(45),
	email varchar(45),
	senha char(10),
	CPF char(11),
	PRIMARY KEY (matricula)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE DEPARTAMENTO
(
    codigo int not null,
	nome varchar(45),
	PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE CURSO
(
	codigo int not null,
    nome varchar(45),
	duracao int,
	departamento int,
	PRIMARY KEY (codigo),
	FOREIGN KEY (departamento) REFERENCES DEPARTAMENTO(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE PROFESSOR
(
	matricula char(9) not null,
	departamento int,
	PRIMARY KEY (matricula),
	FOREIGN KEY (matricula) REFERENCES USUARIO(matricula),
    FOREIGN KEY (departamento) REFERENCES DEPARTAMENTO(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE ALUNO
(
    matricula char(9) not null,
	semestre int,
	curso int,
	PRIMARY KEY (matricula),
	FOREIGN KEY (matricula) REFERENCES USUARIO(matricula),
	FOREIGN KEY (curso) REFERENCES CURSO(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE ORIENTACAO
(
	id int not null AUTO_INCREMENT,
	dt datetime,
	horario datetime,
	observacao varchar(45),
	destinatario char(9),
	remetente char(9),
	PRIMARY KEY (id),
    FOREIGN KEY (destinatario) REFERENCES USUARIO(matricula),
    FOREIGN KEY (remetente) REFERENCES USUARIO(matricula)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE DISCIPLINA
(
	codigo char(10),
	nome varchar(45),
	carga_horaria int,
	PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE ORIENTACAO_DISCIPLINA
(
	disciplina char(10) not null,
	orientacao int not null,
	aprovado smallint,
	cursando smallint,
	PRIMARY KEY (disciplina, orientacao),
	FOREIGN KEY (disciplina) REFERENCES DISCIPLINA(codigo),
	FOREIGN KEY (orientacao) REFERENCES ORIENTACAO(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE OBRIGATORIA
(
	curso int,
	disciplina char(10),
	semestre_sugerido int,
	FOREIGN KEY (curso) REFERENCES CURSO(codigo),
	FOREIGN KEY (disciplina) REFERENCES DISCIPLINA(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE OPTATIVA
(
	curso int,
	disciplina char(10),
	semestre_sugerido int,
	FOREIGN KEY (curso) REFERENCES CURSO(codigo),
	FOREIGN KEY (disciplina) REFERENCES DISCIPLINA(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PREREQUISITO
(
	trancador char(10) not null,
	trancado char(10) not null,
	PRIMARY KEY (trancador, trancado),
    FOREIGN KEY (trancador) REFERENCES DISCIPLINA(codigo),
    FOREIGN KEY (trancado) REFERENCES DISCIPLINA(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
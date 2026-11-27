-- Criação do banco (execute conectado ao banco 'postgres', se ainda não existir)
-- CREATE DATABASE biblioteca_db ENCODING 'UTF8';

CREATE TABLE IF NOT EXISTS livros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    ano INTEGER NOT NULL CHECK (ano >= 0),
    edicao INTEGER NOT NULL CHECK (edicao >= 0)
);

CREATE TABLE IF NOT EXISTS bibliotecarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150),
    endereco VARCHAR(255),
    qntde_emprestimos INTEGER NOT NULL DEFAULT 0 CHECK (qntde_emprestimos >= 0)
);

CREATE INDEX IF NOT EXISTS idx_livros_titulo_lower
    ON livros (LOWER(titulo));
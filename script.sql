SELECT * FROM tb_usuario;

INSERT INTO tb_usuario (login, senha) VALUES
('admin', 'admin'),
('joão', '123456');

--crtl + ; para comentar
-- CREATE TABLE tb_usuario(
--     cod_usuario SERIAL PRIMARY KEY,
--     login VARCHAR(200) NOT NULL,
--     senha VARCHAR(200) NOT NULL
-- );
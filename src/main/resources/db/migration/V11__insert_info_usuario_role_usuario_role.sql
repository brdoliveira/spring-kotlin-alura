INSERT INTO usuario(nome, email, password) VALUES ('admin','admin@email.com','$2a$12$ZuJmMfX/XfvkejWsF85J.eSAN4KVsqmzL9zqms89iljWlk/e5DR4e');
INSERT INTO role(nome) values ('ADMIN');
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2,2);
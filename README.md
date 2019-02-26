# codigo_TCC
Para conseguir acesso ao sistema deve ser feito os seguintes procedimentos no Postgressql:

1. Ter o Postgresql instalado

2. No código no arquivo chamado application.properties, inserir o usuário e senha do Postgressql.

3. No Postgresql crie um database chamado "sgqdb" (sem as aspas)

4.Rodar a seguinte query:

INSERT INTO usuario VALUES (0,true,'Coordenador','teste@teste.com','029.011.628-53','Marcelo','$2a$10$TAj4Z9.aEEKdy8O/21vZReEv1/uJYAPxjhJcwLiIxLftq1MAC.JqC');
INSERT INTO coordenador VALUES(0);
INSERT INTO permissao VALUES (0,'PG_CADASTRA_PROVA')
,(1,'PG_CONSULTAR_PROVA')
,(2,'PG_CADASTRA_QUESTAO')
,(3,'PG_CONSULTAR_QUESTAO')
,(4,'PG_CONSULTAR_PROFESSOR')
,(5,'PG_CADASTRAR_PROFESSOR')
,(6,'PG_CONSULTAR_DISCIPLINA')
,(7,'PG_CADASTRAR_DISCIPLINA')
,(8,'PG_HOMOLOGAR_QUESTAO');

INSERT INTO permissao_usuario VALUES 
(0,0),
(0,1),
(0,2),
(0,3),
(0,4),
(0,5),
(0,6),
(0,7),
(0,8);

5. Rodar o aplicativo e inserir no usuário 029.011.628-53 e a senha 123 e logar no sistema. (Talvez seja necessário atualizar as dependências do maven antes. O aplicativo roda na porta 8080, localhost:8080)

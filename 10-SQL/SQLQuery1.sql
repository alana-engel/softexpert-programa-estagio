USE sesuite_209

--1 - Na tabela ADUSER, traga um relatório em SQL onde diga a quantidade total de usuários que estão ativos e inativos (fguserenabled)
SELECT fguserenabled, COUNT(fguserenabled) AS 'Quantidade usuários Ativos e Inativos' from aduser
GROUP BY fguserenabled;

--2 - Crie um relatório que traga todos os itens de menu (ADMENU) que estão relacionados aos componentes da suite (COPRODUCT). Nesse relatório, você deve imprimir código do menu, o nome do menu, e o nome do componente.
SELECT admenu.cdmenu, admenu.nmmenu, coproduct.nmname   
FROM admenu INNER JOIN coproduct ON admenu.cdisosystem = coproduct.cdproduct;
 
--3 - Continuando a criação do relatório anterior, ordene os registros retornados pelo nome do componente e nome do menu.
SELECT admenu.cdmenu, admenu.nmmenu, coproduct.nmname   
FROM admenu INNER JOIN coproduct ON admenu.cdisosystem = coproduct.cdproduct
ORDER BY coproduct.nmname, admenu.nmmenu;

--4 - Na tabela COACCOUNT, traga todas as contas que tenham o login começando com "ti" e terminam com "ne"
SELECT * FROM coaccount
WHERE idlogin like 'ti%ne';

--5 A tabela ADUSER contém os usuários, a ADACCESSGROUP contém os grupos de acesso, e a tabela ADUSERACCGROUP contém a relação entre as duas tabelas anteriores.
--Sendo assim, faça uma query que retorne o identificador e o nome (idgroup e nmgroup) de todos os grupos de acesso, incluindo uma contagem de quantos usuários existem em cada grupo. Não retorne o grupo se este não conter nenhum usuário
SELECT ADACCESSGROUP.idgroup, ADACCESSGROUP.nmgroup, COUNT(ADUSER.cduser) AS 'Quantidade de Usuários'
FROM ADACCESSGROUP INNER JOIN (ADUSERACCGROUP INNER JOIN ADUSER ON ADUSER.cduser = ADUSERACCGROUP.cduser) ON ADACCESSGROUP.cdgroup = ADUSERACCGROUP.cdgroup
GROUP BY ADACCESSGROUP.idgroup, ADACCESSGROUP.nmgroup
--Faça os inserts necessários para que exista mais de um grupo de acesso (utilize o oidlicensekey do grupo de acesso já existente)
INSERT INTO ADACCESSGROUP (cdgroup,idgroup,nmgroup,oidlicensekey) VALUES (100,'AAA','TESTE','40288109463a356d01463a4295c70071');

--6 Agora, de forma inversa à query anterior, faça uma consulta que retorne o nome de todos os usuários seguidos da quantidade de grupos de acesso em que estão inseridos, ordenados pela quantidade de forma decrescente
SELECT ADUSER.nmuser, COUNT(ADACCESSGROUP.idgroup) AS 'Quantidade de Grupos'
FROM ADACCESSGROUP INNER JOIN (ADUSERACCGROUP INNER JOIN ADUSER ON ADUSER.cduser = ADUSERACCGROUP.cduser) ON ADACCESSGROUP.cdgroup = ADUSERACCGROUP.cdgroup
GROUP BY ADUSER.nmuser

--7 Faça uma consulta que retorne o nome de todos os usuários que não estão vinculados a nenhum grupo de acesso
SELECT ADUSER.nmuser
FROM ADUSER
WHERE NOT EXISTS
    (SELECT * 
     FROM ADUSERACCGROUP
     WHERE ADUSER.cduser = ADUSERACCGROUP.cduser)

--8 Faça uma consulta que retorne o total de usuários que estão vinculados a pelo menos um grupo de acesso
SELECT COUNT(ADUSER.nmuser) AS 'Usuário com grupo de acesso'
FROM ADUSER
WHERE EXISTS
    (SELECT * 
     FROM ADUSERACCGROUP
     WHERE ADUSER.cduser = ADUSERACCGROUP.cduser)

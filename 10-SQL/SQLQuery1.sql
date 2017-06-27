USE sesuite_209

--1 - Na tabela ADUSER, traga um relat�rio em SQL onde diga a quantidade total de usu�rios que est�o ativos e inativos (fguserenabled)
SELECT fguserenabled, COUNT(fguserenabled) AS 'Quantidade usu�rios Ativos e Inativos' from aduser
GROUP BY fguserenabled;

--2 - Crie um relat�rio que traga todos os itens de menu (ADMENU) que est�o relacionados aos componentes da suite (COPRODUCT). Nesse relat�rio, voc� deve imprimir c�digo do menu, o nome do menu, e o nome do componente.
SELECT admenu.cdmenu, admenu.nmmenu, coproduct.nmname   
FROM admenu INNER JOIN coproduct ON admenu.cdisosystem = coproduct.cdproduct;
 
--3 - Continuando a cria��o do relat�rio anterior, ordene os registros retornados pelo nome do componente e nome do menu.
SELECT admenu.cdmenu, admenu.nmmenu, coproduct.nmname   
FROM admenu INNER JOIN coproduct ON admenu.cdisosystem = coproduct.cdproduct
ORDER BY coproduct.nmname, admenu.nmmenu;

--4 - Na tabela COACCOUNT, traga todas as contas que tenham o login come�ando com "ti" e terminam com "ne"
SELECT * FROM coaccount
WHERE idlogin like 'ti%ne';

--5 A tabela ADUSER cont�m os usu�rios, a ADACCESSGROUP cont�m os grupos de acesso, e a tabela ADUSERACCGROUP cont�m a rela��o entre as duas tabelas anteriores.
--Sendo assim, fa�a uma query que retorne o identificador e o nome (idgroup e nmgroup) de todos os grupos de acesso, incluindo uma contagem de quantos usu�rios existem em cada grupo. N�o retorne o grupo se este n�o conter nenhum usu�rio
SELECT ADACCESSGROUP.idgroup, ADACCESSGROUP.nmgroup, COUNT(ADUSER.cduser) AS 'Quantidade de Usu�rios'
FROM ADACCESSGROUP INNER JOIN (ADUSERACCGROUP INNER JOIN ADUSER ON ADUSER.cduser = ADUSERACCGROUP.cduser) ON ADACCESSGROUP.cdgroup = ADUSERACCGROUP.cdgroup
GROUP BY ADACCESSGROUP.idgroup, ADACCESSGROUP.nmgroup
--Fa�a os inserts necess�rios para que exista mais de um grupo de acesso (utilize o oidlicensekey do grupo de acesso j� existente)
INSERT INTO ADACCESSGROUP (cdgroup,idgroup,nmgroup,oidlicensekey) VALUES (100,'AAA','TESTE','40288109463a356d01463a4295c70071');

--6 Agora, de forma inversa � query anterior, fa�a uma consulta que retorne o nome de todos os usu�rios seguidos da quantidade de grupos de acesso em que est�o inseridos, ordenados pela quantidade de forma decrescente
SELECT ADUSER.nmuser, COUNT(ADACCESSGROUP.idgroup) AS 'Quantidade de Grupos'
FROM ADACCESSGROUP INNER JOIN (ADUSERACCGROUP INNER JOIN ADUSER ON ADUSER.cduser = ADUSERACCGROUP.cduser) ON ADACCESSGROUP.cdgroup = ADUSERACCGROUP.cdgroup
GROUP BY ADUSER.nmuser

--7 Fa�a uma consulta que retorne o nome de todos os usu�rios que n�o est�o vinculados a nenhum grupo de acesso
SELECT ADUSER.nmuser
FROM ADUSER
WHERE NOT EXISTS
    (SELECT * 
     FROM ADUSERACCGROUP
     WHERE ADUSER.cduser = ADUSERACCGROUP.cduser)

--8 Fa�a uma consulta que retorne o total de usu�rios que est�o vinculados a pelo menos um grupo de acesso
SELECT COUNT(ADUSER.nmuser) AS 'Usu�rio com grupo de acesso'
FROM ADUSER
WHERE EXISTS
    (SELECT * 
     FROM ADUSERACCGROUP
     WHERE ADUSER.cduser = ADUSERACCGROUP.cduser)

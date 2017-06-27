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


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


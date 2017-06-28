# Desafio 10 - Biblioteca com JDBC

Agora vamos voltar ao sistema de biblioteca que temos até o momento. Inicialmente, crie uma cópia do projeto de biblioteca com maven (última implementação que foi realizada) e coloque neste diretório, seguindo o mesmo padrão de diretórios dos demais desafios.

Se até este ponto não havia tido um bom exemplo do porquê utilizar interfaces e abstrações pra deixar a implementação adaptável, agora teremos um bom motivo: Seu desafio é criar uma nova implementação de repositório para o sistema de biblioteca, permitindo que este armazene e consulte os dados de um banco de dados. Então, assim como temos a implementação padrão que gerencia as coleções de entidades em memória, agora teremos outra implementação para a persistência destas informações em uma estrutura mais robusta, como é o caso de um sistema de banco de dados.

### Requisitos

- Utilize o banco de dados SQLServer ou PostgreSQL. Crie as tabelas, índices, chaves primárias e chaves estrangeiras que achar necessário para armazenar as informações.
- As implementações já existentes de repositório (gravando em memória e arquivo, caso este tenha sido feito) deverão permanecer funcionando normalmente, de preferência **sem nenhuma alteração no código atual**.
- Coloque a dependência do driver JDBC no `pom.xml`.
- Lembre-se sempre de manter as responsabilidades de cada implementação separadas.
 
*Let's do it!*
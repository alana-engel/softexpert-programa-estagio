# Desafio 07 - Biblioteca com Maven

Neste ponto já temos o sistema de biblioteca rodando com seus devidos testes unitários. Sendo assim, o desafio aqui é simples: fazer o projeto utilizar o Maven.

### O que devo fazer?

A ideia aqui não envolve escrever ou modificar nenhum código .java, apenas adequar o projeto já existente do desafio anterior (06-biblioteca-com-testes) na estrutura de um projeto Maven:

 - Coloque todos as classes de código-fonte no diretório padrão do Maven (`src/main/java`)
 - Coloque todas as classes de testes no diretório padrão de testes (`src/test/java`)
 - Utilize a dependência do [JUnit](https://mvnrepository.com/artifact/junit/junit) na sua última versão (4.12). Coloque-a no arquivo `pom.xml`
 - Crie uma forma do Maven gerar um arquivo JAR executável para o projeto, onde você consiga executá-lo em qualquer Sistema Operational, apenas utilizando o comando:

    ```shell
    java -jar SistemaBiblioteca.jar
    ```

 - Não permita que este arquivo seja gerado sem que todos os testes unitários sejam validados sem nenhum erro
 
*Let's do it!*
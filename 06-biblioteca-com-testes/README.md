# Desafio 06 - Biblioteca com testes

## Parte I

Chegou a hora de pensarmos um pouco na qualidade do nosso código. Vamos implementar testes unitários automatizados para o nosso sistema de bibliotecas.

### Observações

- Você deverá criar testes unitários automatizados para as operações de validação com os livros/autores/categorias. Ex: Criar teste para validar um livro sem título, um autor sem nome ou um livro com data de aquisição superior ao dia de hoje
- Tente fazer com que cada método de teste valide apenas um comportamento de uma funcionalidade específica.
- É interessante que cada teste seja independente.
- Utilize a combinação Eclipse + [JUnit](http://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar) para criar e executar seus testes.

### Exemplo de teste

O exemplo abaixo mostra como poderia ser implementado o teste para validar que o livro contém (ou não) um autor.

```java
public class LivroTest {
    
    @Test
    public void deveValidarLivroSeExistirPeloMenosUmAutor() throws Exception {
        Livro livro = new Livro();
        //livro.setXXX(...);
        Autor autor = new Autor();
        //autor.setXXX(...);
        livro.addAutor(autor);
        boolean isValid = livro.validate();
        Assert.assertTrue(isValid);
    }

    @Test(expected = LivroSemAutorException.class)
    public void deveLancarExceptionAoValidarLivroSemAutor() throws Exception {
        Livro livro = new Livro();
        //livro.setXXX(...);
        livro.setAutor(null);
        livro.validate();
    }

    // another tests...
}
```

Perceba que no primeiro caso estamos esperando que ao informar um autor, o método `validate()` retorne verdadeiro. E no segundo teste esperamos que uma exceção seja lançada ao tentar validar um livro sem nenhum autor.

Este é apenas um exemplo, isso não quer dizer que seu código tenha que ser assim ou ser testado da mesma forma.

Podemos dizer que quanto mais fácil for implementar testes para um código, melhor ele está. E a recíproca também é verdadeira, portanto se você perceber que está difícil separar as funcionalidades do seu sistema para então testá-las, provavelmente seu código está muito acoplado e deveria estar mais separado.

_Let's do it!_

---

## Parte II

Agora, vamos incrementar um pouco o código da biblioteca fazendo uma pequena (mas bem testável) adaptação: Vamos gerar um código de barras "real" para os livros.

O código de barras tradicionalmente usado no comércio é o EAN, e dentro deste tipo existem códigos com diferentes quantidades numéricas. O código que vamos gerar aqui é o EAN 13, que possui (pasme) 13 dígitos, sendo 12 deles compostos por valores determinados e o último, o dígito verificador. Sua tarefa aqui é gerar um código EAN 13 válido que será composto da seguinte forma:

* Os 3 primeiros dígitos se referem ao código do país, neste caso será sempre 789 (padrão brasileiro)
* Os 5 dígitos seguintes são compostos pelo código da "empresa". Utilize aqui o número que quiser, exceto "00000". Porém, o número que você usar aqui, deve ser usado para todos os livros da biblioteca
* Os próximos 4 dígitos são formados pelo código do produto. Utilize aqui o código sequencial que foi gerado para o livro. Ex: se o código do livro for 15, então os 4 dígitos serão "0015"
* O último número dígito, como já foi comentado, é o dígito verificador. Este dígito é gerado a partir de uma série de cálculos efetuados sobre os 12 números já existentes, e anexado ao final do código de barras. Assim, o código se torna um padrão válido, que pode ser expresso no formado de código de barras, e interpretado por qualquer leitor.

Composição do código de barras:

[![6910641a29338cb00a34c3f8fb501831.jpg](https://pichoster.net/images/2017/06/06/6910641a29338cb00a34c3f8fb501831.jpg)](https://pichoster.net/image/CmuBb)

Para gerar o dígito verificador:

```none
Suponhamos que estamos usando o código de barras : 789162731405 e queremos saber qual é o digito final (verificador)
Some todos os dígitos das posições ímpares (dígitos 7, 9, 6, 7, 1 e 0): 7 + 9 + 6 + 7 + 1 + 0 = 30
Some todos os dígitos das posições pares (dígitos 8, 1, 2, 3, 4 e 5): 8 + 1 + 2 + 3 + 4 + 5 = 23
Multiplique a soma dos dígitos das posições pares por 3, veja: 23 * 3 = 69
Some os dois resultados das etapas anteriores, veja: 30 + 69 = 99
Determine o número que deve ser adicionado ao resultado da soma para se criar um múltiplo de 10 (resto), veja: 99 + 1 = 100
Portanto, o dígito verificador é 1.
```

Após isso, crie testes que validem códigos já existentes (procure por códigos de produtos válidos) para garantir que o seu algoritmo funcione.

Não esqueça de desacoplar ao máximo a implementação desta nova funcionalidade. Crie os testes que achar necessário e mão na massa!

_Let's do it... again!_

---

> _Any fool can write code that a computer can understand. Good programmers write code that humans can understand. (Martin Fowler, 2008)_
package com.softexpert.library.operations;

public enum UserOperations {
    
    CADASTRAR_AUTOR("Cadastrar autor", AuthorOperations.cadastrarAutor()),
    CADASTRAR_CATEGORIA("Cadastrar categoria", CategoryOperations.cadastrarCategoria()),
    CADASTRAR_LIVRO("Cadastrar livro", BookOperations.cadastrarLivro()),
    ALTERAR_LIVRO("Alterar livro", BookOperations.alterarLivro()),
    LISTAR_AUTORES("Listar todos os autores", AuthorOperations.listarAutores()),
    LISTAR_AUTORES_POR_NOME("Listar autores por nome", AuthorOperations.listarAutoresPorNome()),
    LISTAR_LIVROS("Listar todos os livros", BookOperations.listarLivros()),
    LISTAR_LIVROS_POR_AUTOR("Listar livros por autor", BookOperations.listarLivrosPeloNomeDoAutor()),
    SAIR("Sair", new ExitOperation());
    
    private String nome;
    private ConsoleOperation operacao;

    UserOperations(String nome, ConsoleOperation operacao) {
        this.nome = nome;
        this.operacao = operacao;
    }
    
    public static UserOperations valueOfOpcao(int opcao) {
        for (UserOperations operacao : values()) {
            if (opcao == operacao.getCodigo()) {
                return operacao;
            }
        }
        return null;
    }
    
    public ConsoleOperation getConsoleOperation() {
        return operacao;
    }
    
    public int getCodigo() {
        return ordinal() + 1;
    }
    
    @Override
    public String toString() {
        return getCodigo() + " - " + nome;
    }
}

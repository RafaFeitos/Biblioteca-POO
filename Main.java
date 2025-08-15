package com.biblioteca;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Biblioteca minhaBiblioteca = new Biblioteca();

        // 1. Cadastrar livros e revistas
        Livro livro1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954);
        Revista revista1 = new Revista("National Geographic", "Vários", 2023);
        Livro livro2 = new Livro("1984", "George Orwell", 1949);

        minhaBiblioteca.cadastrarItem(livro1);
        minhaBiblioteca.cadastrarItem(revista1);
        minhaBiblioteca.cadastrarItem(livro2);

        // 2. Cadastrar usuários
        Usuario usuario1 = new Usuario("Alice", "U001");
        Usuario usuario2 = new Usuario("Bob", "U002");

        minhaBiblioteca.cadastrarUsuario(usuario1);
        minhaBiblioteca.cadastrarUsuario(usuario2);

        // 3. Listar itens disponíveis (inicialmente todos)
        minhaBiblioteca.listarItensDisponiveis();

        // 4. Realizar empréstimos
        minhaBiblioteca.realizarEmprestimo(usuario1, livro1, 7); // Alice empresta O Senhor dos Anéis por 7 dias
        minhaBiblioteca.realizarEmprestimo(usuario2, revista1, 5); // Bob empresta National Geographic por 5 dias
        minhaBiblioteca.realizarEmprestimo(usuario1, livro2, 10); // Alice empresta 1984 por 10 dias

        // Tentar emprestar um item já emprestado
        minhaBiblioteca.realizarEmprestimo(usuario2, livro1, 3); 

        // Tentar emprestar mais do que o limite (Alice já tem 2, limite é 3)
        Livro livro3 = new Livro("A Revolução dos Bichos", "George Orwell", 1945);
        minhaBiblioteca.cadastrarItem(livro3);
        minhaBiblioteca.realizarEmprestimo(usuario1, livro3, 7); // Alice tenta emprestar A Revolução dos Bichos

        // 5. Listar itens emprestados e disponíveis após empréstimos
        minhaBiblioteca.listarItensEmprestados();
        minhaBiblioteca.listarItensDisponiveis();

        // 6. Simular atraso (para demonstração, vamos ajustar a data de devolução prevista de um empréstimo)
        // Isso é apenas para simulação. Em um sistema real, a data de devolução prevista não seria alterada.
        // Para simular um atraso, o empréstimo de Alice para o livro1 teria a data de devolução prevista no passado.
        // Vamos simular que o empréstimo de Alice para o livro1 deveria ter sido devolvido há 2 dias.
        // NOTA: Em um cenário real, a data de devolução prevista é fixa. A simulação de atraso ocorre ao comparar
        // a data de devolução real com a data de devolução prevista.
        // Para este exemplo, vamos forçar a data de devolução prevista do livro1 para o passado para simular atraso.
        // Isso não é uma boa prática em produção, mas serve para demonstração.
        for (Emprestimo emp : minhaBiblioteca.getEmprestimos()) {
            if (emp.getUsuario().equals(usuario1) && emp.getItem().equals(livro1)) {
                // Simula que a data de devolução prevista era 2 dias atrás
                emp.setDataDevolucaoPrevista(LocalDate.now().minusDays(2)); 
                break;
            }
        }

        // 7. Realizar devoluções
        minhaBiblioteca.realizarDevolucao(usuario1, livro1); // Devolução com atraso
        minhaBiblioteca.realizarDevolucao(usuario2, revista1); // Devolução sem atraso

        // 8. Listar itens emprestados e disponíveis após devoluções
        minhaBiblioteca.listarItensEmprestados();
        minhaBiblioteca.listarItensDisponiveis();
    }
}


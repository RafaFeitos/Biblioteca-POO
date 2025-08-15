package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String id;
    private List<Emprestimo> emprestimos;
    private final int LIMITE_EMPRESTIMOS = 3; // Exemplo de limite de empréstimos

    public Usuario(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.emprestimos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public boolean podeEmprestar() {
        return emprestimos.size() < LIMITE_EMPRESTIMOS;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + ", ID: " + id + ", Empréstimos Ativos: " + emprestimos.size();
    }
}

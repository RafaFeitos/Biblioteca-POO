package com.biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    private List<ItemBiblioteca> itens;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.itens = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void cadastrarItem(ItemBiblioteca item) {
        this.itens.add(item);
        System.out.println(item.getTipo() + " \"" + item.getTitulo() + "\" cadastrado com sucesso.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        System.out.println("Usuário \"" + usuario.getNome() + "\" cadastrado com sucesso.");
    }

    public void realizarEmprestimo(Usuario usuario, ItemBiblioteca item, int diasEmprestimo) {
        if (item.isDisponivel()) {
            if (usuario.podeEmprestar()) {
                LocalDate dataEmprestimo = LocalDate.now();
                LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(diasEmprestimo);
                Emprestimo novoEmprestimo = new Emprestimo(usuario, item, dataEmprestimo, dataDevolucaoPrevista);
                this.emprestimos.add(novoEmprestimo);
                usuario.adicionarEmprestimo(novoEmprestimo);
                item.setDisponivel(false);
                System.out.println("Empréstimo de \"" + item.getTitulo() + "\" para \"" + usuario.getNome() + "\" realizado com sucesso.");
            } else {
                System.out.println("Empréstimo não realizado: Usuário \"" + usuario.getNome() + "\" atingiu o limite de empréstimos.");
            }
        } else {
            System.out.println("Empréstimo não realizado: \"" + item.getTitulo() + "\" não está disponível.");
        }
    }

    public void realizarDevolucao(Usuario usuario, ItemBiblioteca item) {
        Emprestimo emprestimoParaDevolver = null;
        for (Emprestimo emp : emprestimos) {
            if (emp.getUsuario().equals(usuario) && emp.getItem().equals(item) && emp.getDataDevolucaoReal() == null) {
                emprestimoParaDevolver = emp;
                break;
            }
        }

        if (emprestimoParaDevolver != null) {
            LocalDate dataDevolucaoReal = LocalDate.now();
            emprestimoParaDevolver.setDataDevolucaoReal(dataDevolucaoReal);
            item.setDisponivel(true);
            usuario.removerEmprestimo(emprestimoParaDevolver);

            long diasAtraso = ChronoUnit.DAYS.between(emprestimoParaDevolver.getDataDevolucaoPrevista(), dataDevolucaoReal);
            if (diasAtraso > 0) {
                emprestimoParaDevolver.setAtrasado(true);
                double penalidade = diasAtraso * 0.5; // Exemplo: 0.5 por dia de atraso
                emprestimoParaDevolver.setPenalidade(penalidade);
                System.out.println("Devolução de \"" + item.getTitulo() + "\" por \"" + usuario.getNome() + "\" realizada com atraso de " + diasAtraso + " dias. Penalidade: R$ " + String.format("%.2f", penalidade));
            } else {
                System.out.println("Devolução de \"" + item.getTitulo() + "\" por \"" + usuario.getNome() + "\" realizada com sucesso.");
            }
        } else {
            System.out.println("Devolução não realizada: Empréstimo não encontrado para \"" + item.getTitulo() + "\" e \"" + usuario.getNome() + "\".");
        }
    }

    public void listarItensDisponiveis() {
        System.out.println("\n--- Itens Disponíveis ---");
        itens.stream()
             .filter(ItemBiblioteca::isDisponivel)
             .forEach(System.out::println);
        System.out.println("-------------------------");
    }

    public void listarItensEmprestados() {
        System.out.println("\n--- Itens Emprestados ---");
        emprestimos.stream()
                   .filter(emp -> emp.getDataDevolucaoReal() == null)
                   .forEach(System.out::println);
        System.out.println("-------------------------");
    }

    public List<ItemBiblioteca> getItens() {
        return itens;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}


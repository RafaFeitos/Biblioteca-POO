package com.biblioteca;

import java.time.LocalDate;

public class Emprestimo {
    private Usuario usuario;
    private ItemBiblioteca item;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private boolean atrasado;
    private double penalidade;

    public Emprestimo(Usuario usuario, ItemBiblioteca item, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.atrasado = false;
        this.penalidade = 0.0;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ItemBiblioteca getItem() {
        return item;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public boolean isAtrasado() {
        return atrasado;
    }

    public void setAtrasado(boolean atrasado) {
        this.atrasado = atrasado;
    }

    public double getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(double penalidade) {
        this.penalidade = penalidade;
    }

    @Override
    public String toString() {
        return "Empréstimo: " + item.getTitulo() + " para " + usuario.getNome() +
               ", Empréstimo: " + dataEmprestimo + ", Devolução Prevista: " + dataDevolucaoPrevista +
               (dataDevolucaoReal != null ? ", Devolução Real: " + dataDevolucaoReal : "") +
               (atrasado ? ", ATRASADO (Penalidade: " + penalidade + ")" : "");
    }
}

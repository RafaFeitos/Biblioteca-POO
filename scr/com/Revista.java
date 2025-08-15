package com.biblioteca;

public class Revista implements ItemBiblioteca {
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;
    private String editora;

    // public void setEditora(String editora) {
    //     this.editora = editora;
    // }

    public Revista(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.editora = editora;
        this.disponivel = true; // Revista inicialmente disponível
   
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public int getAno() {
        return ano;
    }

    @Override
    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String toString() {
        return "Revista: " + titulo + ", Autor: " + autor + ", Ano: " + ano + ", Disponível: " + disponivel;
    }

    //implementação futura
    public String getEditora() {
        return editora;
    }
}

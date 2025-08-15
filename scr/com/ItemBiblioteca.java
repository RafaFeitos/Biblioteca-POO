package com.biblioteca;

public interface ItemBiblioteca {
    String getTitulo();
    String getAutor();
    int getAno();
    boolean isDisponivel();
    void setDisponivel(boolean disponivel);
    String getTipo();
}

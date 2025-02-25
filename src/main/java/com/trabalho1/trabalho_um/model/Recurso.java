package com.trabalho1.trabalho_um.model;

public class Recurso {
    protected String nome;
    protected int quantidade;
    
    public Recurso(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    @Override
    public String toString() {
        return "Recurso{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}

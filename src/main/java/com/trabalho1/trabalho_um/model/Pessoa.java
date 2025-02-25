package com.trabalho1.trabalho_um.model;

public class Pessoa {
    protected String nome;
    protected int idade;
    protected String identificador;
    
    public Pessoa() {
    }
    
    public Pessoa(String nome, int idade, String identificador) {
        this.nome = nome;
        this.idade = idade;
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getIdentificador() {
        return identificador;
    }
    
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}

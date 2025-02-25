package com.trabalho1.trabalho_um.model;
import java.util.ArrayList;
import java.util.List;

public class Sobrevivente extends Pessoa {
    protected List<String> habilidades;
    protected String status;

    public Sobrevivente() {
        super();
        this.habilidades = new ArrayList<>();
    }

    public Sobrevivente(String nome, int idade, String identificador, List<String> habilidades, String status) {
        super(nome, idade, identificador);
        this.habilidades = habilidades != null ? habilidades : new ArrayList<>();
        this.status = status;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Sobrevivente{" +
                "nome='" + getNome() + '\'' +
                ", idade=" + getIdade() +
                ", identificador='" + getIdentificador() + '\'' +
                ", habilidades=" + habilidades +
                ", status='" + status + '\'' +
                '}';
    }
}
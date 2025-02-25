package com.trabalho1.trabalho_um.model;

import java.util.List;

public class Missao {
    protected String nome;
    protected String objetivo;
    protected String local;
    protected List<String> sobreviventes;
    protected List<Recurso> recursosColetados;
    protected String status;

    public Missao(String nome, String objetivo, String local, List<String> sobreviventes, List<Recurso> recursosColetados, String status) {
        this.nome = nome;
        this.objetivo = objetivo;
        this.local = local;
        this.sobreviventes = sobreviventes;
        this.recursosColetados = recursosColetados;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public List<String> getSobreviventes() {
        return sobreviventes;
    }
    public void setSobreviventes(List<String> sobreviventes) {
        this.sobreviventes = sobreviventes;
    }
    public List<Recurso> getRecursosColetados() {
        return recursosColetados;
    }
    public void setRecursosColetados(List<Recurso> recursosColetados) {
        this.recursosColetados = recursosColetados;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        StringBuilder recursos = new StringBuilder();
        for (Recurso recurso : recursosColetados) {
            recursos.append(recurso.toString()).append(", ");
        }
        
        return "Missao{" +
                "nome='" + nome + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", local='" + local + '\'' +
                ", sobreviventes=" + sobreviventes +
                ", recursosColetados=" + recursos +
                ", status='" + status + '\'' +
                '}';
    }
}

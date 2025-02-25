package com.trabalho1.trabalho_um.model;

import java.util.ArrayList;
import java.util.List;

public class Vault {
    protected List<Sobrevivente> sobreviventes;
    protected List<Recurso> recursos;
    protected List<Missao> missoes;

    public Vault() {
        this.sobreviventes = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.missoes = new ArrayList<>();
    }

    public Vault(List<Sobrevivente> sobreviventes, List<Recurso> recursos, List<Missao> missoes) {
        this.sobreviventes = sobreviventes;
        this.recursos = recursos;
        this.missoes = missoes;
    }

    public List<Sobrevivente> getSobreviventes() {
        return sobreviventes;
    }

    public void setSobreviventes(List<Sobrevivente> sobreviventes) {
        this.sobreviventes = sobreviventes;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public List<Missao> getMissoes() {
        return missoes;
    }

    public void setMissoes(List<Missao> missoes) {
        this.missoes = missoes;
    }

    public void adicionarRecursos(List<Recurso> novosRecursos) {
        if (novosRecursos != null) {
            recursos.addAll(novosRecursos);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vault{");
        sb.append("sobreviventes=[");
        for (Sobrevivente s : sobreviventes) {
            sb.append(s.toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        sb.append(", recursos=[");
        for (Recurso r : recursos) {
            sb.append(r.toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        sb.append(", missoes=[");
        for (Missao m : missoes) {
            sb.append(m.toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        sb.append("}");
        return sb.toString();
    }

}

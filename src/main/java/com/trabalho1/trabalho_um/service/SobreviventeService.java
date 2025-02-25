package com.trabalho1.trabalho_um.service;

import com.trabalho1.trabalho_um.model.Sobrevivente;
import com.trabalho1.trabalho_um.model.Vault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SobreviventeService {
    private final Vault vault;

    @Autowired
    public SobreviventeService(Vault vault) {
        this.vault = vault; 
    }

    public String salvarSobrevivente(Sobrevivente sobrevivente) {
        //System.out.println(vault.toString());
        Sobrevivente sobreviventeEncontrado = getSobreviventeById(sobrevivente.getIdentificador());
        if (sobreviventeEncontrado == null) {
            vault.getSobreviventes().add(sobrevivente);
            return "Sucesso";
        } else {
            return "Erro";
        }
    }

    public List<Sobrevivente> getSobreviventes() {
        return vault.getSobreviventes();
    }

    public List<Sobrevivente> getSobreviventesMissao() {
        List<Sobrevivente> sobreviventesAptos = new ArrayList<>();
        for (Sobrevivente sobrevivente : vault.getSobreviventes()) {
            if ("Ativo".equals(sobrevivente.getStatus()) || "Ferido".equals(sobrevivente.getStatus())) {
                sobreviventesAptos.add(sobrevivente);
            }
        }
        return sobreviventesAptos;
    }

    public Sobrevivente getSobreviventeById(String id) {

        for (Sobrevivente sobrevivente : vault.getSobreviventes()) {
            if (sobrevivente.getIdentificador().equals(id)) {
                return sobrevivente;
            }
        }
        return null;
    }

    public void atualizarSobrevivente(Sobrevivente sobrevivente) {
        removerSobrevivente(sobrevivente.getIdentificador());
        vault.getSobreviventes().add(sobrevivente);
    }

    public void removerSobrevivente(String id) {
        for (Sobrevivente sobrevivente : vault.getSobreviventes()) {
            if (sobrevivente.getIdentificador().equals(id)) {
                vault.getSobreviventes().remove(sobrevivente);
                break;
            }
        }
    }

    public List<Sobrevivente> getSobreviventesByIds(List<String> ids) {
        List<Sobrevivente> result = new ArrayList<>();
        for (String id : ids) {
            Sobrevivente sobrevivente = getSobreviventeById(id);
            if (sobrevivente != null) {
                result.add(sobrevivente);
            }
        }
        return result;
    }
}

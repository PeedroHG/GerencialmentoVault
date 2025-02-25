package com.trabalho1.trabalho_um.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trabalho1.trabalho_um.model.Recurso;
import com.trabalho1.trabalho_um.model.Vault;

@Service
public class RecursoService {
    private final Vault vault;

    @Autowired
    public RecursoService(Vault vault) {
        this.vault = vault;
    }

    public void cadastrarRecurso(Recurso recurso) {
        //System.out.println(vault.toString());
        vault.getRecursos().add(recurso);
    }

    public List<Recurso> listarRecursos() {
        return vault.getRecursos();
    }

}

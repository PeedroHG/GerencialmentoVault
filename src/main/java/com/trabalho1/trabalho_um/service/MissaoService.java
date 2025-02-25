package com.trabalho1.trabalho_um.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trabalho1.trabalho_um.model.Missao;
import com.trabalho1.trabalho_um.model.Recurso;
import com.trabalho1.trabalho_um.model.Vault;

@Service
public class MissaoService {
    private final Vault vault;

    @Autowired
    public MissaoService(Vault vault) {
        this.vault = vault;
    }
    public void salvarMissao(Missao missao) {
        //System.out.println(vault.toString());
        vault.getMissoes().add(missao);
        if ("Sucesso".equals(missao.getStatus())) {
            List<Recurso> recursosColetados = missao.getRecursosColetados();
            vault.adicionarRecursos(recursosColetados);
        }
    }

    public List<Missao> getMissoes() {
        return vault.getMissoes();
    }

}

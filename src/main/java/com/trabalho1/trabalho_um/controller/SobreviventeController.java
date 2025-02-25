package com.trabalho1.trabalho_um.controller;

import com.trabalho1.trabalho_um.model.Sobrevivente;
import com.trabalho1.trabalho_um.service.SobreviventeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class SobreviventeController {

    @Autowired
    private SobreviventeService sobreviventeService;

    @GetMapping("/cadastrar-sobrevivente")
    public String mostrarFormularioCadastro() {
        return "cadastrar-sobrevivente.html";
    }

    @PostMapping("/cadastrar-sobrevivente")
    @ResponseBody
    public String cadastrarSobrevivente(@RequestBody Sobrevivente sobrevivente) {
        String resultado = sobreviventeService.salvarSobrevivente(sobrevivente);
        if (resultado == "Sucesso") {
            return "Sucesso";
        } else {
            return "Erro";
        }
    }

    @GetMapping("/listar-sobreviventes")
    @ResponseBody
    public List<Sobrevivente> listarSobreviventes() {
        return sobreviventeService.getSobreviventes();
    }

    @GetMapping("/listar-sobreviventes-missoes")
    @ResponseBody
    public List<Sobrevivente> listarSobreviventesMissoes() {
        return sobreviventeService.getSobreviventesMissao();
    }

    @GetMapping("/obter-sobrevivente/{id}")
    @ResponseBody
    public Sobrevivente obterSobrevivente(@PathVariable String id) {
        Sobrevivente sobrevivente = sobreviventeService.getSobreviventeById(id);
        return sobrevivente;
    }

    @PutMapping("/atualizar-sobrevivente/{id}")
    @ResponseBody
    public String atualizarSobrevivente(@RequestBody Sobrevivente sobrevivente) {
        sobreviventeService.atualizarSobrevivente(sobrevivente);
        return "Dados atualizados com sucesso!";
    }

    @DeleteMapping("/remover-sobrevivente/{id}")
    @ResponseBody
    public String removerSobrevivente(@PathVariable String id) {
        sobreviventeService.removerSobrevivente(id);
        return "Dados removidos com sucesso!";
    }

    @PostMapping("/buscar-sobreviventes")
    @ResponseBody
    public List<Sobrevivente> buscarSobreviventesJson(@RequestBody List<String> ids) {
        return sobreviventeService.getSobreviventesByIds(ids);
    }
}
package com.trabalho1.trabalho_um.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trabalho1.trabalho_um.model.Recurso;
import com.trabalho1.trabalho_um.service.RecursoService;

@Controller
public class RecursoController {
    @Autowired
    private RecursoService recursoService;

    @GetMapping("/listar-recursos")
    @ResponseBody
    public List<Recurso> listarRecursos() {
        return recursoService.listarRecursos();
    }

    @PostMapping("/cadastrar-recurso")
    @ResponseBody
    public String cadastrarRecurso(@RequestBody Recurso recurso) {
        recursoService.cadastrarRecurso(recurso);
        return "Dados recebidos com sucesso!";
    }

    @PostMapping("/consumir-recurso")
    @ResponseBody
    public String consumirRecurso(@RequestBody Recurso recurso) {
        List<Recurso> recursos = recursoService.listarRecursos();
        for (Recurso r : recursos) {
            if (r.getNome().equalsIgnoreCase(recurso.getNome()) && r.getQuantidade() >= recurso.getQuantidade()) {
                recursos.remove(r);
                return "Recurso consumido com sucesso!";
            }
        }
        return "Erro ao consumir recurso!";
    }

}

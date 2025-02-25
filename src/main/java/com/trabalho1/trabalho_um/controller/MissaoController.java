package com.trabalho1.trabalho_um.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trabalho1.trabalho_um.model.Missao;
import com.trabalho1.trabalho_um.service.MissaoService;

@Controller
public class MissaoController {

    @Autowired
    private MissaoService missaoService;

    @GetMapping("/cadastrar-missao")
    public String mostrarFormularioCadastro() {
        return "cadastrar-missao.html";
    }

    @GetMapping("/listar-missoes")
    @ResponseBody
    public List<Missao> listarMissoes() {
        return missaoService.getMissoes();
    }

    @PostMapping("/cadastrar-missao")
    @ResponseBody
    public String cadastrarMissao(@RequestBody Missao missao) {
        missaoService.salvarMissao(missao);
        return "Dados recebidos com sucesso!";
    }
}

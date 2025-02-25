let selecionados = [];
var recursos = [];

function abrirModalCadastro() {
    $('#modal-cadastro').show();
}

function Voltar() {
    window.location.href = "vault.html";
}

function fecharModalCadastro() {
    $('#modal-cadastro').hide();
    $('#modal-recursos').hide();
    $('#modal-sobreviventes').hide();
}

function carregarMissao() {
    $.ajax({
        url: '/listar-missoes',
        type: 'GET',
        success: function (response) {
            response.forEach(function (missao) {
                atualizarTabelaMissao(missao);
            });
        },
        error: function (error) {
            alert("Erro ao carregar missões!");
        }
    });
}


function atualizarTabelaMissao(missao) {

    var sobreviventes = missao.sobreviventes.join(', ');
    var recursos = missao.recursosColetados.map(recurso => `${recurso.quantidade} ${recurso.nome}`).join(', ');

    if (recursos.length <= 0) {
        recursos = "Nenhum recurso coletado.";
    }

    if (sobreviventes.length <= 0) {
        sobreviventes = "Nenhum sobrevivente cadastrado.";
    }
    //console.log(recursos)
    var linha = '<tr>' +
        '<td class="icones"><i class="fa-solid fa-box" onclick="exibirRecursos(\'' + recursos + '\')"></i>' +
        '<i class="fa-solid fa-user" onclick="exibirSobreviventes(\'' + sobreviventes + '\')"></i></td>' +
        '<td class="nome">' + missao.nome + '</td>' +
        '<td class="objetivo">' + missao.objetivo + '</td>' +
        '<td class="local">' + missao.local + '</td>' +
        '<td class="status">' + missao.status + '</td>' +
        '</tr>';
    $('#tabela-missoes').append(linha);
}

function abrirModalRecursos() {
    $('#modal-recursos').show();
}

function exibirRecursos(recursos) {
    abrirModalRecursos();

    $('#lista-recursos-modal').empty();
    recursos.split(', ').forEach(function (recurso) {
        $('#lista-recursos-modal').append('<li>' + recurso + '</li>');
    });
    //console.log(recursos);
}

function abrirModalSobreviventes() {
    $('#modal-sobreviventes').show();
}

function exibirSobreviventes(sobreviventes) {
    abrirModalSobreviventes()
    sobreviventes = sobreviventes.split(', ').map(String);

    $('#lista-sobreviventes-modal').empty();
    $.ajax({
        url: '/buscar-sobreviventes',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(sobreviventes),
        success: function (response) {
            //console.log("sucesso", response);
            response.forEach(function (sobrevivente) {
                $('#lista-sobreviventes-modal').append('<li>' + sobrevivente.nome + ' (ID: ' + sobrevivente.identificador + ')</li>');
            });
        },
        error: function (error) {
            alert("Erro ao carregar sobreviventes!");
        }
    });
}


function carregarSobreviventes() {
    $.ajax({
        url: '/listar-sobreviventes-missoes',
        type: 'GET',
        success: function (response) {
            response.forEach(function (sobrevivente) {
                atualizarTabela(sobrevivente);
            });
        },
        error: function (error) {
            alert("Erro ao carregar sobreviventes!");
        }
    });
}

function atualizarTabela(sobrevivente) {
    var habilidades = sobrevivente.habilidades.join(', ');

    if (habilidades.length <= 0) {
        habilidades = "Nenhuma habilidade cadastrada.";
    }

    var linha = '<tr>' +
        '<td><input type="checkbox" onclick="selecionarSobrevivente(' + sobrevivente.identificador + ', this.checked)"></td>' +
        '<td class="nome">' + sobrevivente.nome + '</td>' +
        '<td class="idade">' + sobrevivente.idade + '</td>' +
        '<td class="identificador">' + sobrevivente.identificador + '</td>' +
        '<td class="habilidades">' + habilidades + '</td>' +
        '<td class="status">' + sobrevivente.status + '</td>' +
        '</tr>';
    $('#tabela-sobreviventes').append(linha);
}

function selecionarSobrevivente(id, isChecked) {
    if (isChecked) {
        selecionados.push(id);
    } else {
        const index = selecionados.indexOf(id);
        if (index > -1) {
            selecionados.splice(index, 1);
        }
    }
}

function adicionarRecurso() {
    var quantidade = $('#quantidade').val();
    var recurso = $('#recurso').val();

    if (!recurso || quantidade <= 0) {
        alert("Preencha os campos de recurso para adiciona-los!");
        return;
    }

    novoRecurso = {
        quantidade: quantidade,
        nome: recurso
    };

    recursos.push(novoRecurso);
    $('#lista-recurso').append('<li>' + novoRecurso.quantidade + ' ' + novoRecurso.nome + '<span class="del" onclick="removerRecurso(this)"> &times;</span>' + '</li>');
}

function removerRecurso(elemento) {
    var recurso = $(elemento).parent().text().trim().split(' ')[1];
    var index = recursos.findIndex(r => r.recurso === recurso);
    if (index > -1) {
        recursos.splice(index, 1);
    }
    $(elemento).parent().remove();
    //console.log(recursos);
}

function cadastrarMissao() {
    var nome = $('#nome').val();
    var objetivo = $('#objetivo').val();
    var local = $('#local').val();
    var status = $('#status').val();

    if (selecionados.length <= 0) {
        alert("Selecione ao menos um sobrevivente para cadastrar a missão!");
        return;
    }

    var dadosMissao = {
        nome: nome,
        objetivo: objetivo,
        local: local,
        status: status,
        sobreviventes: selecionados,
        recursosColetados: recursos
    };

    $.ajax({
        url: '/cadastrar-missao',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dadosMissao),
        success: function () {
            carregarMissao();
            fecharModalCadastro();
        },
        error: function () {
            alert("Erro ao cadastrar missão!");
        }
    });
}

function validarFormulario() {
    const form = document.getElementById('form-missao');
    if (form.checkValidity()) {
        cadastrarMissao();
    } else {
        form.reportValidity();
    }
}

carregarMissao();
carregarSobreviventes();
carregarSobreviventes()
habilidades = [];

function Voltar() {
    window.location.href = "vault.html";
}

function abrirModalCadastro(type, id) {
    if (type === 'cadastrar') {
        $('input[name="nome"]').val('');
        $('input[name="idade"]').val('');
        $('input[name="id"]').val('');
        $('select[name="status"]').val('');
        habilidades = [];
        $('#lista-habilidades').empty();
        $('#modal-cadastro').show();
    } else {
        $('#botao-cadastro').attr('onclick', 'salvarEdicao()');
        $.ajax({
            url: '/obter-sobrevivente/' + id,
            type: 'GET',
            success: function (response) {
                $('input[name="nome"]').val(response.nome);
                $('input[name="idade"]').val(response.idade);
                $('input[name="id"]').val(response.identificador);
                $('select[name="status"]').val(response.status);

                if (response.status === 'Morto') {
                    $('select[name="status"]').attr('disabled', true);
                } else {
                    $('select[name="status"]').attr('disabled', false);
                }

                habilidades = response.habilidades;
                $('#lista-habilidades').empty();
                response.habilidades.forEach(function (habilidade) {
                    $('#lista-habilidades').append('<li>' + habilidade + '<span class="del" onclick="removerHabilidade(this)"> &times;</span>' + '</li>');
                });
                $('#modal-cadastro').show();
            },
            error: function (error) {
                alert("Erro ao obter dados do sobrevivente!");
            }
        });
    }
}

function salvarEdicao() {
    let nome = $('input[name="nome"]').val();
    let idade = $('input[name="idade"]').val();
    let identificador = $('input[name="id"]').val();
    let status = $('select[name="status"]').val();

    let sobrevivente = {
        nome: nome,
        idade: idade,
        identificador: identificador,
        habilidades: habilidades,
        status: status
    };

    $.ajax({
        url: '/atualizar-sobrevivente/' + identificador,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(sobrevivente),
        success: function (response) {
            location.reload();
        },
        error: function (error) {
            alert("Erro ao atualizar dados!");
        }
    });
}

function fecharModalCadastro() {
    $('#modal-cadastro').hide();
}

function carregarSobreviventes() {
    $.ajax({
        url: '/listar-sobreviventes',
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
        '<td><i class="fa-solid fa-pencil" onclick="editarSobrevivente(' + sobrevivente.identificador + ')"></i><i class="fa-solid fa-trash" onclick="removerSobrevivente(' + sobrevivente.identificador + ')"></i></td>' +
        '<td class="nome">' + sobrevivente.nome + '</td>' +
        '<td class="idade">' + sobrevivente.idade + '</td>' +
        '<td class="identificador">' + sobrevivente.identificador + '</td>' +
        '<td class="habilidades">' + habilidades + '</td>' +
        '<td class="status">' + sobrevivente.status + '</td>' +
        '</tr>';
    $('#tabela-sobreviventes').append(linha);
}

function editarSobrevivente(id) {
    abrirModalCadastro('editar', id);
}

function removerSobrevivente(id) {
    $.ajax({
        url: '/remover-sobrevivente/' + id,
        type: 'DELETE',
        success: function () {
            location.reload();
        },
        error: function (error) {
            alert("Erro ao remover sobrevivente!");
        }
    });
}

function adicionarHabilidade() {
    if (habilidades.length >= 3) {
        alert("Você só pode adicionar no máximo 3 habilidades.");
        return;
    }
    var habilidade = $('#habilidade').val();
    if (habilidades.includes(habilidade)) {
        alert("Esta habilidade já foi cadastrada.");
        return;
    }
    habilidades.push(habilidade);
    $('#lista-habilidades').append('<li>' + habilidade + '<span class="del" onclick="removerHabilidade(this)"> &times;</span>' + '</li>');
}

function removerHabilidade(elemento) {
    var habilidade = $(elemento).parent().text().trim();
    var index = habilidades.indexOf(habilidade);
    habilidades.splice(index, 1);
    $(elemento).parent().remove();
}

function validarFormulario() {
    const form = document.getElementById('form-sobrevivente');
    if (form.checkValidity()) {
        cadastrarSobrevivente();
    } else {
        form.reportValidity();
    }
}

function cadastrarSobrevivente() {
    let nome = $('input[name="nome"]').val();
    let idade = $('input[name="idade"]').val();
    let identificador = $('input[name="id"]').val();
    let status = $('select[name="status"]').val();

    let sobrevivente = {
        nome: nome,
        idade: idade,
        identificador: identificador,
        habilidades: habilidades,
        status: status
    };

    $.ajax({
        url: '/cadastrar-sobrevivente',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(sobrevivente),
        success: function (response) {
            if (response == 'Sucesso') {
                atualizarTabela(sobrevivente);
                fecharModalCadastro();
            } else {
                alert("Sobrevivente com mesmo ID!");
            }

        },
        error: function (error) {
            alert("Erro ao enviar dados!");
        }
    });
}


listarRecursos();

function Voltar() {
    window.location.href = "vault.html";
}

function listarRecursos() {
    //console.log("predroca")

    $.ajax({
        url: '/listar-recursos',
        method: 'get',
        success: function (res) {
            //console.log(res)
            $('#tabela-recursos').empty();
            res.forEach(recurso => {
                $('#tabela-recursos').append(
                    `<tr style="margin-top: 10px">
                        <td class="consumir"><i class="fa-solid fa-xmark" onclick="consumirRecurso('${recurso.nome}', ${recurso.quantidade})"></i></td>
                        <td>${recurso.nome}</td>
                        <td class="quantidade">${recurso.quantidade}</td>
                    </tr>`
                );
            });
        }
    });
}

function consumirRecurso(nome, quantidade) {
    $.ajax({
        url: '/consumir-recurso',
        method: 'post',
        contentType: 'application/json',
        data: JSON.stringify({
            nome: nome,
            quantidade: quantidade
        }),
        success: function (res) {
            listarRecursos();
        },
        error: function (res) {
            alert('Erro ao consumir recurso!');
        }
    });
}
function cadastrarRecurso() {
    var recurso = $('#recurso').val();
    var quantidade = $('#quantidade').val();

    if (recurso == null || quantidade == null || quantidade == 0) {
        alert("Preencha todos os campos!");
        return;
    }


    $.ajax({
        url: '/cadastrar-recurso',
        method: 'post',
        contentType: 'application/json',
        data: JSON.stringify({
            nome: recurso,
            quantidade: quantidade
        }),
        success: function (res) {
            alert("Sucesso ao cadastrar recurso!");
            listarRecursos();
            $('#recurso').val('');
            $('#quantidade').val('');
        },
        error: function (res) {
            alert('Erro ao cadastrar recurso!');
        }
    });
}

function abrirModalCadastroRecurso() {
    $('#modal-recurso').show();
}

function fecharModalCadastroRecurso() {
    $('#modal-recurso').hide();
}

function fazGet(url) {
    let request = new XMLHttpRequest()
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function criaLinhaProduto(produto) {
    console.log(produto)
    linha = document.createElement("tr");
    tdId = document.createElement("td");
    tdNome = document.createElement("td");
    tdDescricao = document.createElement("td");
    tdQuantidade = document.createElement("td");

    tdId.innerHTML = produto.id
    tdNome.innerHTML = produto.nome
    tdDescricao.innerHTML = produto.descricao
    tdQuantidade.innerHTML = produto.quantidade

    linha.appendChild(tdId);
    linha.appendChild(tdNome);
    linha.appendChild(tdDescricao);
    linha.appendChild(tdQuantidade);

    return linha;
}

function criaLinhaFiscalizador(fiscalizador) {
    console.log(fiscalizador)
    linha = document.createElement("tr");
    tdId = document.createElement("td");
    tdNome = document.createElement("td");
    tdDescricao = document.createElement("td");
    tdId.innerHTML = fiscalizador.id
    tdNome.innerHTML = fiscalizador.nome
    tdDescricao.innerHTML = fiscalizador.descricao

    linha.appendChild(tdId);
    linha.appendChild(tdNome);
    linha.appendChild(tdDescricao);

    return linha;
}

function criaLinhaDonatario(donatario) {
    console.log(donatario)
    linha = document.createElement("tr");
    tdId = document.createElement("td");
    tdNome = document.createElement("td");
    tdDescricao = document.createElement("td");
    tdTelefone =document.createElement("td");
    tdEndereco = document.createElement("td");

    tdId.innerHTML = donatario.id
    tdNome.innerHTML = donatario.nome
    tdDescricao.innerHTML = donatario.descricao
    tdTelefone.innerHTML = donatario.telefone
    tdEndereco.innerHTML = donatario.endereco

    linha.appendChild(tdId);
    linha.appendChild(tdNome);
    linha.appendChild(tdDescricao);
    linha.appendChild(tdTelefone);
    linha.appendChild(tdEndereco);

    return linha;
}

function criaLinhaLote(lote) {
    console.log(lote)
    linha = document.createElement("tr");

    tdId = document.createElement("td");
    tdNome = document.createElement("td");
    tdObservacao = document.createElement("td");
    tdFiscalizador = document.createElement("td");
    tdDonatario = document.createElement("td");

    tdId.innerHTML = lote.id
    tdNome.innerHTML = lote.nome
    tdObservacao.innerHTML = lote.observacao
    tdFiscalizador.innerHTML = lote.fiscalizador.nome
    tdDonatario.innerHTML = lote.donatario.nome

    linha.appendChild(tdId);
    linha.appendChild(tdNome);
    linha.appendChild(tdObservacao);
    linha.appendChild(tdFiscalizador);
    linha.appendChild(tdDonatario);

    return linha;
}

function main() {
    let data = fazGet("http://127.0.0.1:8080/produto");
    let produtos = JSON.parse(data);
    let tabela = document.getElementById("tabelaProduto");
    produtos.forEach(element => {
        let linha = criaLinhaProduto(element);
        tabela.appendChild(linha);
    });

    data = fazGet("http://127.0.0.1:8080/fiscalizador");
    let fiscalizadores = JSON.parse(data);
    tabela = document.getElementById("tabelaFiscalizador");
    fiscalizadores.forEach(element => {
        let linha = criaLinhaFiscalizador(element);
        tabela.appendChild(linha);
    });

    data = fazGet("http://127.0.0.1:8080/donatario");
    let donatarios = JSON.parse(data);
    tabela = document.getElementById("tabelaDonatario");
    donatarios.forEach(element => {
        let linha = criaLinhaDonatario(element);
        tabela.appendChild(linha);
    });

}

main()
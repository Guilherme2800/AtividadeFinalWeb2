function fazGet(url) {
    let request = new XMLHttpRequest()
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function criaLinhaLoteDonatario(lote) {
    console.log(lote)
    linha = document.createElement("tr");

    tdId = document.createElement("td");
    tdNome = document.createElement("td");
    tdObservacao = document.createElement("td");
    tdDataEntrega = document.createElement("td");
    tdFiscalizador = document.createElement("td");
    tdDonatario = document.createElement("td");
    tdProdutos = document.createElement("td");

    tdId.innerHTML = lote.id
    tdNome.innerHTML = lote.nome
    tdObservacao.innerHTML = lote.observacao
    tdDataEntrega.innerHTML = lote.dataEntrega
    tdFiscalizador.innerHTML = lote.fiscalizador.nome
    tdDonatario.innerHTML = lote.donatario.nome

    let produtos = new Array(lote.produto);
    let nomeProdutos = "";
    for(let i = 0; i < produtos[0].length; i++){
        nomeProdutos += produtos[0][i].nome + ","
    }

    tdProdutos.innerHTML = nomeProdutos;

    linha.appendChild(tdId);
    linha.appendChild(tdNome);
    linha.appendChild(tdObservacao);
    linha.appendChild(tdDataEntrega);
    linha.appendChild(tdFiscalizador);
    linha.appendChild(tdDonatario);
    linha.appendChild(tdProdutos);

    return linha;
}

function buscarLoteDonatario(id) {
    document.getElementById("tabelaDonatario").innerHTML = "";
    let data = fazGet("http://127.0.0.1:8080/lote/donatario/"+id);
    let donatarios = JSON.parse(data);
    let tabela = document.getElementById("tabelaDonatario");
    donatarios.forEach(element => {
        let linha = criaLinhaLoteDonatario(element);
        tabela.appendChild(linha);
    });
}

function main() {
    let data = fazGet("http://127.0.0.1:8080/donatario");
    let donatarios = JSON.parse(data);
    let div = document.getElementById("donatarios");
    debugger;
    donatarios.forEach(element => {
        div.innerHTML += "<button onClick = \"(buscarLoteDonatario("+element.id+"))\">"+element.nome+"</button>"
    });
}

main()
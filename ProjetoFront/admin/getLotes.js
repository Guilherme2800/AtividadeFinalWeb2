function criaLinhaLote(lote) {
    console.log(lote)
    linha = document.createElement("tr");

    tdId = document.createElement("td");
    tdNome = document.createElement("td");
    tdObservacao = document.createElement("td");
    tdDataEntrega = document.createElement("td");
    tdFiscalizador = document.createElement("td");
    tdDonatario = document.createElement("td");
    tdProdutos = document.createElement("td");
    tdAcao = document.createElement("td");

    tdId.innerHTML = lote.id
    tdNome.innerHTML = lote.nome
    tdObservacao.innerHTML = lote.observacao
    tdDataEntrega.innerHTML = lote.dataEntrega
    tdFiscalizador.innerHTML = lote.fiscalizador.nome
    tdDonatario.innerHTML = lote.donatario.nome
    debugger;
    let produtos = new Array(lote.produto);
    let nomeProdutos = "";
    for(let i = 0; i < produtos[0].length; i++){
        nomeProdutos += produtos[0][i].nome + ","
    }
    debugger;
    tdProdutos.innerHTML = nomeProdutos;
    tdAcao.innerHTML = "<button onclick=\"deletarLote("+lote.id+")\"> Deletar </button>"

    linha.appendChild(tdId);
    linha.appendChild(tdNome);
    linha.appendChild(tdObservacao);
    linha.appendChild(tdDataEntrega);
    linha.appendChild(tdFiscalizador);
    linha.appendChild(tdDonatario);
    linha.appendChild(tdProdutos);
    linha.appendChild(tdAcao);

    return linha;
}

function fazGet(url) {
    let request = new XMLHttpRequest()
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function main() {
    this.data = fazGet("http://127.0.0.1:8080/lote");
    let lotes = JSON.parse(this.data);
    let tabela = document.getElementById("tabelaLotes");
    lotes.forEach(element => {
        let linha = criaLinhaLote(element);
        tabela.appendChild(linha);
    });

}

main()
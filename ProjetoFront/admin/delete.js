function fazDelete(url, body) {
    console.log("Body=", body)
    let request = new XMLHttpRequest()
    request.open("DELETE", url, true)
    request.setRequestHeader("Content-type", "application/json")
    request.send(JSON.stringify(body))

    request.onload = function() {
        console.log(this.responseText)
    }

    return request.responseText
}

function deletarLote(id) {
    let url = "http://127.0.0.1:8080/lote/"+id;

    body = {}

    fazDelete(url, body)

    setTimeout(() => {
        document.getElementById("refresh").click();
    }, 500);
    
}
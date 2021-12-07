function consulta() {
    $.ajax({
        url:"http://129.151.98.129:8080/api/Message/all",
        type:"GET",
        datatype:"JSON",
        success:function(response){
            $("#resultado").empty();
            mostrarResultado(response);
            console.log(response);
        }
    });
}

function mostrarResultado(response){
    let rows = '<table>\
                    <tr>\
                        <td>room</td>\
                        <td>message</td>\
                    </tr>';
    for(i = 0; i < response.length; i++){
        rows += '<tr>'
        rows += '<td>' + response[i].room.name + '</td>';
        rows += '<td>' + response[i].messageText + '</td>';
        rows += '<td> <button onclick="borrar(' + response[i].idMessage + ')"> Borrar </td>';
        rows += '<td> <button onclick="elemEspecifico(' + response[i].idMessage + ')"> Cargar </td>';
        rows += '</tr>';
    }
    rows += '</table>';
    $("#resultado").append(rows);
}

function guardarInfo(){
    let datos={
        messageText:$("#message").val(),
        client:{idClient:$("#client").val()},
        room:{id:$("#room").val()}
    };
    console.log(datos);
    let datosEnvio = JSON.stringify(datos)
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        url:"http://129.151.98.129:8080/api/Message/save",
        data:datosEnvio,
        type:"POST",
        datatype:"json",
        success:function(respuesta){
            alert("Información guardada");
            limpiarFormulario();
        }
    });
}

function borrar(idItem){
    $.ajax({
        url:"http://129.151.98.129:8080/api/Message/" + idItem,
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type:"DELETE",
        datatype:"json",
        success:function(respuesta){
            $("resultado").empty();
            consulta();
            alert("Elemento eliminado");
        }
    });
}

function elemEspecifico(idItem){
    $.ajax({
        url:"http://129.151.98.129:8080/api/Message/" + idItem,
        type:"GET",
        datatype:"JSON",
        success:function(response){
            console.log(response)
            $("#idMessage").val(response.idMessage)
            $("#message").val(response.messageText)
            $("#client").val(response.client.idClient)
            $("#room").val(response.room.id)
        }
    });
}

function editar(){
    let datos={
        idMessage:$("#idMessage").val(),
        messagetext:$("#message").val()
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datosEnvio);
    $.ajax({
        url:"http://129.151.98.129:8080/api/Message/update",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        data:datosEnvio,
        contentType:'application/json',
        type:"PUT",
        datatype:"json",
        success:function(respuesta){
            $("#resultado").empty();
            consulta();
            alert("Información actualizada");
        }
    });
    limpiarFormulario();
}

function limpiarFormulario(){
    $("#message").val("");
    $("#client").val("");
    $("#room").val("");
}
function consulta() {
    $.ajax({
        url:"http://129.151.98.129:8080/api/Reservation/all",
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
                    <td>id</td>\
                    <td>start date</td>\
                    <td>devolution date</td>\
                    <td>client id</td>\
                    <td>client name</td>\
                    <td>client email</td>\
                    <td>reservation score</td>\
                </tr>';
    for(i = 0; i < response.length; i++){
        rows += '<tr>'
        rows += '<td>' + response[i].idReservation + '</td>';
        rows += '<td>' + response[i].startDate + '</td>';
        rows += '<td>' + response[i].devolutionDate + '</td>';
        rows += '<td>' + response[i].client.idClient + '</td>';
        rows += '<td>' + response[i].client.name + '</td>'
        rows += '<td>' + response[i].client.email + '</td>';
        rows += '<td>' + response[i].score + '</td>';
        rows += '<td> <button onclick="borrar(' + response[i].idReservation + ')"> Borrar </td>';
        rows += '<td> <button onclick="elemEspecifico(' + response[i].idReservation + ')"> Cargar </td>';
        rows += '</tr>';
    }
    rows += '</table>';
    $("#resultado").append(rows);

}

function guardarInfo(){
    let today = new Date();
    let z = today.getTimezoneOffset() * 60 * 1000;
    let tLocal = today - z;
    tLocal = new Date(tLocal);
    let iso = tLocal.toISOString() 
    console.log(iso);

    let datos={
        startDate:iso,
        devolutionDate:$("#devolutionDate").val(),
        client:{idClient:$("#client").val()},
        room:{id:$("#room").val()},
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datos)
    console.log(datosEnvio);
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        url:"http://129.151.98.129:8080/api/Reservation/save",
        data:datosEnvio,
        type:"POST",
        datatype:"json",
        success:function(respuesta){
            alert("Información guardada");
            limpiarFormulario();
        }
    });
}


function elemEspecifico(idItem){
    $.ajax({
        url:"http://129.151.98.129:8080/api/Reservation/" + idItem,
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta)
            $("#idReservation").val(respuesta.idReservation)
            $("#devolutionDate").val(respuesta.devolutionDate)
            $("#client").val(respuesta.client.idClient)
            $("#room").val(respuesta.room.id)
        }
    });
}

function editar(){
    let datos={
        idReservation:$("#idReservation").val(),
        devolutionDate:$("#devolutionDate").val(),
        client:{idClient:$("#client").val()},
        room:{id:$("#room").val()},
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datosEnvio);
    $.ajax({
        url:"http://129.151.98.129:8080/api/Reservation/update",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        data:datosEnvio,
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

function borrar(idItem){
    $.ajax({
        url:"http://129.151.98.129:8080/api/Reservation/" + idItem,
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



function limpiarFormulario(){
    $("#name").val("");
    $("#description").val("");
}
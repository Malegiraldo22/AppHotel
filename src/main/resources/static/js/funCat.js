function consulta() {
    $.ajax({
        url:"http://129.151.98.129:8080/api/Category/all",
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
                    <td>name</td>\
                    <td>description</td>\
                    <td>rooms</td>\
                </tr>';
    for(i = 0; i < response.length; i++){
        rows += '<tr>'
        rows += '<td>' + response[i].name + '</td>';
        rows += '<td>' + response[i].description + '</td>';
        rows += '<td>' + response[i].rooms.name + '</td>';
        rows += '<td> <button onclick="borrar(' + response[i].id + ')"> Borrar </td>';
        rows += '<td> <button onclick="elemEspecifico(' + response[i].id + ')"> Cargar </td>';
        rows += '</tr>';
    }
    rows += '</table>';
    $("#resultado").append(rows);
}

function guardarInfo(){
    let datos={
        name:$("#name").val(),
        description:$("#description").val(),
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datos)
    console.log(datosEnvio);
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        url:"http://129.151.98.129:8080/api/Category/save",
        data:datosEnvio,
        type:"POST",
        datatype:"json",
        success:function(respuesta){
            alert("Información guardada");
            limpiarFormulario();
            consulta();
        }
    });
}


function elemEspecifico(idItem){
    $.ajax({
        url:"http://129.151.98.129:8080/api/Category/" + idItem,
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta)
            $("#id").val(respuesta.id),
            $("#name").val(respuesta.name),
            $("#description").val(respuesta.description)
        }
    });
}

function editar(){
    let datos={
        id:$("#id").val(),
        name:$("#name").val(),
        description:$("#description").val()
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datosEnvio);
    $.ajax({
        url:"http://129.151.98.129:8080/api/Category/update",
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
        url:"http://129.151.98.129:8080/api/Category/" + idItem,
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
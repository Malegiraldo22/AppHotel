function consulta() {
    $.ajax({
        url:"http://129.151.98.129:8080/api/Admin/all",
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
                    <td>name</td>\
                    <td>email</td>\
                    <td>age</td>\
                </tr>';
    for(i = 0; i < response.length; i++){
        rows += '<tr>'
        rows += '<td>' + response[i].idAdmin + '</td>';
        rows += '<td>' + response[i].name + '</td>';
        rows += '<td>' + response[i].email + '</td>';
        rows += '<td> <button onclick="borrar(' + response[i].idAdmin + ')"> Borrar </td>';
        rows += '<td> <button onclick="elemEspecifico(' + response[i].idAdmin + ')"> Cargar </td>';
        rows += '</tr>';
    }
    rows += '</table>';
    $("#resultado").append(rows);
}

function guardarInfo(){
    let datos={
        email:$("#email").val(),
        password:$("#password").val(),
        name:$("#name").val(),
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datosEnvio);
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        url:"http://129.151.98.129:8080/api/Admin/save",
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

function borrar(idItem){
    $.ajax({
        url:"http://129.151.98.129:8080/api/Admin/" + idItem,
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
        url:"http://129.151.98.129:8080/api/Admin/" + idItem,
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type:"GET",
        datatype:"JSON",
        success:function(response){
            console.log(response)
            $("#name").val(response.name),
            $("#email").val(response.email),
            $("#age").val(response.age)
        }
    });
}

function editar(){
    let datos={
        id:$("#id").val(),
        name:$("#name").val(),
        email:$("#email").val(),
        age:$("#age").val(),
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datosEnvio);
    $.ajax({
        url:"http://129.151.98.129:8080/api/Admin/update",
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

function editarInfo(){
    if(validarFormulaio() == true){
        enviarInfo();
    }
}

function limpiarFormulario(){
    $("#id").val("");
    $("#name").val("");
    $("#email").val("");
    $("#password").val("");
    $("#age").val("");
}

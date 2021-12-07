function consulta() {
    $.ajax({
        url:"http://129.151.98.129:8080/api/Client/all",
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
        rows += '<td>' + response[i].idClient + '</td>';
        rows += '<td>' + response[i].name + '</td>';
        rows += '<td>' + response[i].email + '</td>';
        rows += '<td>' + response[i].age + '</td>';
        rows += '<td> <button onclick="borrar(' + response[i].idClient + ')"> Borrar </td>';
        rows += '<td> <button onclick="elemEspecifico(' + response[i].idClient + ')"> Cargar </td>';
        rows += '</tr>';
    }
    rows += '</table>';
    $("#resultado").append(rows);
}

function guardarInfo(){
    let datos={
        name:$("#name").val(),
        email:$("#email").val(),
        passowrd:$("#password").val(),
        age:$("#age").val(),
    };
    console.log(datos);
    let datosEnvio = JSON.stringify(datos)
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        url:"http://129.151.98.129:8080/api/Client/save",
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
        url:"http://129.151.98.129:8080/api/Client/" + idItem,
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
        url:"http://129.151.98.129:8080/api/Client/" + idItem,
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type:"GET",
        datatype:"JSON",
        success:function(response){
            console.log(response)
            $("#idClient").val(response.idClient),
            $("#name").val(response.name),
            $("#email").val(response.email),
            $("#age").val(response.age)
        }
    });
}

function editar(){
    let datos={
        idClient:$("#idClient").val(),
        name:$("#name").val(),
        email:$("#email").val(),
        password:$("password").val(),
        age:$("#age").val(),
    };
    let datosEnvio = JSON.stringify(datos)
    console.log(datosEnvio);
    $.ajax({
        url:"http://129.151.98.129:8080/api/Client/update",
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

function limpiarFormulario(){
    $("#id").val("");
    $("#name").val("");
    $("#email").val("");
    $("#password").val("");
    $("#age").val("");
}
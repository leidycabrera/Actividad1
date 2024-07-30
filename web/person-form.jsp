<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Este archivo se carga dentro del modal -->
<h3>${person == null ? 'Agregar cliente' : 'Editar Cliente'}</h3>
<form action="person?action=${person == null ? 'insert' : 'update'}" method="post">
    <input type="hidden" name="id" value="${person != null ? person.id : ''}">
    <div class="form-group">
        <label for="name">Nombre:</label>
        <input type="text" class="form-control" id="name" name="name" value="${person != null ? person.name : ''}">
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" name="email" value="${person != null ? person.email : ''}">
    </div>
    <div class="form-group">
        <label for="age">Edad:</label>
        <input type="number" class="form-control" id="age" name="age" value="${person != null ? person.age : ''}">
    </div>
    <div class="form-group">
        <label for="rol">Cargo:</label>
        <input type="text" class="form-control" id="rol" name="rol" value="${person != null ? person.rol : ''}">
    </div>
    <button type="submit" id="boton_formulario" class="btn btn-primary">${person == null ? 'Insertar' : 'Actualizar'}</button>
</form>



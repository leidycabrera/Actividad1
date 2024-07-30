<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="main-content">
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <div class="card mb-4">
                    <div class="card-body">
                        <button type="button" id="add_person" class="btn btn-primary btn-custom" data-toggle="modal" data-target="#personModal" onclick="loadForm()">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/>
                            </svg>
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Email</th>
                                    <th>Edad</th>
                                    <th>Cargo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="person" items="${listPerson}">
                                    <tr>
                                        <td>${person.name}</td>
                                        <td>${person.email}</td>
                                        <td>${person.age}</td>
                                        <td>${person.rol}</td>
                                        <td class="action-links">
                                            <a id="editar_cliente" onclick="loadEdit(${person.id})" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#personModal">Editar</a>
                                            <a href="person?action=delete&id=${person.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro?');">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Modal -->
<div class="modal fade" id="personModal" tabindex="-1" role="dialog" aria-labelledby="personModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Form content will be loaded here -->
            </div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>

<script>
    function loadForm(id) {
        const url = id ? `person-form.jsp?id=${id}` : 'person-form.jsp';
        fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.querySelector('.modal-body').innerHTML = data;
                })
                .catch(error => console.error('Error:', error));
    }

    function loadEdit(id) {
        $.ajax({
            url: "person?action=edit&id="+id,
            success: function (result) {
                document.querySelector('.modal-body').innerHTML = result;
            }
        });
    };
</script>


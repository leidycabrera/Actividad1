<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Actividad 1</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="style.css" type="text/css"/>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" id="loadInicio">Gestión de usuarios</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" id="loadInicio2">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="loadPersonList">Ver todos los usuarios</a>
                    </li>
                </ul>
            </div>
        </nav>   

        <!<!-- Contenedor que carga todas las solicitudes -->
        <div id="container">
            <!-- Hero Section -->
            <header class="hero-section">
                <div class="container">
                    <h1>Bienvenido</h1>
                    <p>Seleccione un cargo</p>
                    <div id="searchRol"></div>
                </div>
            </header>
            <div id="inicio">
                <!-- Main Content -->
                <main>
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card mb-12">
                                    <div class="card-body">
                                        <p class="card-text">Sin resultados</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="loadContent"></div>
        </div>

        <!-- Footer -->
        <footer class="footer">
            <p>&copy; 2024. Todos los derechos reservados.</p>
        </footer>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- jQuery para la solicitud AJAX -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script>
            $(document).ready(function () {
                cargarRoles();
                $("#loadPersonList").click(function (event) {
                    event.preventDefault();
                    $.ajax({
                        url: "person?action=list",
                        success: function (result) {
                            $("#inicio").hide();
                            $("#loadContent").html(result);
                        }
                    });
                });
                $("#loadInicio, #loadInicio2").click(function (event) {
                    event.preventDefault();
                    cargarRoles();
                    $("#inicio").show();
                    $("#loadContent").html('');
                });

                function cargarRoles() {
                    $.ajax({
                        url: "person?action=rols",
                        success: function (result) {
                            $("#searchRol").html(result);
                        }
                    });
                }
            });
        </script>
    </body>
</html>

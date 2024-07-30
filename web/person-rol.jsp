<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select class="form-control" id="cargo" name="cargo">
    <c:forEach var="cargo" items="${cargos}">
        <option value="${cargo}">${cargo}</option>
    </c:forEach>
</select>
<input type="submit" id="filtrar_clientes" class="btn btn-primary" value="Filtrar">

<script>
    $(document).ready(function () {
        $("#filtrar_clientes").click(function (event) {
            event.preventDefault();
            var cargo = $("#cargo").val();
            $.ajax({
                url: "person?action=filtrar&cargo="+cargo,
                success: function (result) {
                    $("#inicio").hide();
                    $("#loadContent").html(result);
                }
            });
        });
    });
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tarea5: Session HTTP</title>
</head>
<body>
<h3>Tarea5: Session HTTP</h3>
<p>Hola <%=session.getAttribute("nombre") != null ? session.getAttribute("nombre") : "anónimo"%>, bienvenido a la
    tarea5.</p>
<form action="/webapp-session-tarea5/guardar-session" method="post">
    <div>
        <label for="nombre">Ingrese su nombre de sesión</label>
        <div>
            <input type="text" name="nombre" id="nombre">
        </div>
    </div>
    <div>
        <input type="submit" value="Enviar">
    </div>
</form>

</body>
</html>

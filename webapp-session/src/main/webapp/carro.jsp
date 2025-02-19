<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.webapp.headers.models.*" %>
<% Carro carro = (Carro) session.getAttribute("carro");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
<h1>Carro de compras</h1>
<% if (carro == null || carro.getItems().isEmpty()) {%>
<p>No hay productos en el carro compra</p>
<%} else {%>
<form action="/webapp-session/actualizar-carro" method="post" name="formCarro">
<table>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Tipo</th>
        <th>Precio</th>
        <th>Cantidad</th>
        <th>Total</th>
        <th>Borrar</th>
    </tr>
    <% for (ItemCarro item : carro.getItems()) {%>
        <tr>
            <td><%=item.getProducto().getId()%></td>
            <td><%=item.getProducto().getNombre()%></td>
            <td><%=item.getProducto().getTipo()%></td>
            <td><%=item.getProducto().getPrecio()%></td>
            <td><input type="text" name="cantidad_<%=item.getProducto().getId()%>" value="<%=item.getCantidad()%>"></td>
            <td><%=item.getImporte()%></td>
            <td><input type="checkbox" name="eliminarProductoCheckbox" value="<%=item.getProducto().getId()%>"></td>
        </tr>
    <%}%>
    <tr>
        <td colspan="5" style="text-align: right">Total</td>
        <td><%=carro.getTotal()%></td>
    </tr>
</table>
    <a href="javascript:document.formCarro.submit();">Actualizar</a>
</form>
<%}%>
<p><a href="<%=request.getContextPath()%>/productos">Seguir comprando</a></p>
<p><a href="<%=request.getContextPath()%>/index.html">Volver</a></p>
</body>
</html>
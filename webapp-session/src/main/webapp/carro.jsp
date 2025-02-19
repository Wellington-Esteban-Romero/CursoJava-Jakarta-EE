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
<table>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Tipo</th>
        <th>Precio</th>
        <th>Cantidad</th>
        <th>Total</th>
    </tr>
    <% for (ItemCarro item : carro.getItems()) {%>
        <tr>
            <td><%=item.getProducto().getId()%></td>
            <td><%=item.getProducto().getNombre()%></td>
            <td><%=item.getProducto().getTipo()%></td>
            <td><%=item.getProducto().getPrecio()%></td>
            <td><%=item.getCantidad()%></td>
            <td><%=item.getImporte()%></td>
        </tr>
    <%}%>
    <tr>
        <td colspan="5" style="text-align: right">Total</td>
        <td><%=carro.getTotal()%></td>
    </tr>
</table>
<%}%>
<p><a href="<%=request.getContextPath()%>/productos">Seguir comprando</a></p>
<p><a href="<%=request.getContextPath()%>/index.html">Volver</a></p>
</body>
</html>
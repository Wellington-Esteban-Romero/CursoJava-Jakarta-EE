<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Cambiar color</title>
  </head>
  <body>
  <h3 style="color: ${cookie.color.getValue()}">Tarea 4: cambiar el color de los textos</h3><h3></h3>
  <p style="color: ${cookie.color.getValue()}">Este es un texto que cambia de color según la elección</p>
  <form action="/webapp-cookie-tarea4/cambiar-color" method="get">
    <div>
      <label >Cambiar color</label>
    </div>
    <div>
      <select name="color" id="color">
        <option value="blue">Azul</option>
        <option value="red">Rojo</option>
        <option value="green">Verde</option>
        <option value="aqua">Aqua</option>
        <option value="BlueViolet">Violeta</option>
        <option value="Gray">Gris</option>
        <option value="Cyan">Cyan</option>
      </select>
    </div>
    <div>
      <input type="submit" value="Cambiar">
    </div>
  </form>
  </body>
</html>

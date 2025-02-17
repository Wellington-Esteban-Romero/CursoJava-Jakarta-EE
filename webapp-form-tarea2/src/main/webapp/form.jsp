<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.Map" %>
<% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Producto</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3 class="mt-5">Formulario Producto!</h3>
    <form action="/webapp-form-tarea2/crear" method="post">
        <div class="row mb-3">
            <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
            <div class="col-sm-4">
                <input type="text" name="nombre" id="nombre" class="form-control" value="${param.nombre}">
            </div>
            <% if (errores != null && errores.containsKey("nombre")) {
                out.println("<small  class='alert alert-danger col-sm-4'style='color: red;'>" + errores.get("nombre") + "</small>");
            }
            %>
        </div>
        <div class="row mb-3">
            <label for="precio" class="col-form-label col-sm-2">Precio</label>
            <div class="col-sm-4">
                <input type="text" name="precio" id="precio" class="form-control" value="${param.precio}">
            </div>
            <% if (errores != null && errores.containsKey("precio")) {
                out.println("<small  class='alert alert-danger col-sm-4'style='color: red;'>" + errores.get("precio") + "</small>");
            }
            %>
        </div>
        <div class="row mb-3">
            <label for="fabricante" class="col-form-label col-sm-2">Fabricante</label>
            <div class="col-sm-4">
                <input type="text" name="fabricante" id="fabricante" class="form-control" value="${param.fabricante}"
                minlength="4" maxlength="10">
            </div>
            <% if (errores != null && errores.containsKey("fabricante")) {
                out.println("<small  class='alert alert-danger col-sm-4'style='color: red;'>" + errores.get("fabricante") + "</small>");
            }
            %>
        </div>
        <!-- Select -->
        <div class="row mb-3">
            <label for="categoria" class="col-form-label col-sm-2">Categorías</label>
            <div class="col-sm-4">
                <select name="categoria" id="categoria" class="form-select">
                    <option value="">-- Seleccionar --</option>
                    <option value="CAT_1" ${param.categoria.equals("CAT_1")? "selected": ""}>Ratones</option>
                    <option value="CAT_2" ${param.categoria.equals("CAT_2")? "selected": ""}>Monitores</option>
                    <option value="CAT_3" ${param.categoria.equals("CAT_3")? "selected": ""}>Teclados</option>
                    <option value="CAT_4" ${param.categoria.equals("CAT_4")? "selected": ""}>Portatiles</option>
                    <option value="CAT_5" ${param.categoria.equals("CAT_5")? "selected": ""}>Placa base</option>
                    <option value="CAT_6" ${param.categoria.equals("CAT_6")? "selected": ""}>Memorias RAM</option>
                </select>
            </div>
            <% if (errores != null && errores.containsKey("categoria")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("categoria") + "</small>");
            }
            %>
        </div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Enviar" class="btn btn-primary">
            </div>
        </div>
    </form>
</div>
</body>
</html>

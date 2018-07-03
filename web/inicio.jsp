<%-- 
    Document   : inicio
    Created on : 03-jul-2018, 10:03:54
    Author     : FAT
--%>

<%
    String msg = request.getParameter("msg");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login jugador</title>
    </head>
    <body>
        <form method="get" action="loginServlet">
            <label for="txtNombre">Nombre de usuario: </label>
            <input type ="text" name="usuario"><br>
            <label for="txtPass">Contraseña: </label>
            <input type ="text" name="password"><br>  
            <input type="submit" value="Login"></input>
            <%if(msg!=null){%> 
                 <h1> Error: <%=msg%>
            <%}%>                 
       </form>
    </body>
</html>

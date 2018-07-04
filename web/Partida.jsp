<%-- 
    Document   : Partida
    Created on : 03-jul-2018, 11:30:27
    Author     : FAT
--%>

<%
    String msg = request.getParameter("msg");
    String estado = request.getParameter("estado");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="http://code.jquery.com/jquery-latest.min.js"></script>
         <script type="text/javascript">

            var vistaWeb = new EventSource("partidaServlet?accion=new");
                                    
            vistaWeb.onerror = function(evento) {
               alert("Sin conexion con el servidor");
                vistaWeb.close();
                document.location="/partida/";
            }
            
            vistaWeb.addEventListener("nombreJugador", function (evento){
                document.title = evento.data;                
            },false);
            vistaWeb.addEventListener("listaJugadores", function (evento){
                document.getElementById("spanJugadores").innerHTML = evento.data;
            },false);
            vistaWeb.addEventListener("listaJugadoresMano", function (evento){
                document.getElementById("spanJugadoresMano").innerHTML = evento.data;
            },false);
            vistaWeb.addEventListener("contador", function (evento){
                document.getElementById("spanContador").innerHTML = evento.data;
            },false);
            vistaWeb.addEventListener("mensaje", function (evento){
                alert(evento.data);
            },false);
            
            function crearContacto(){
                var nom = $("#nombre").val();
                var nro = $("#numero").val();
                var pos = $("#cboTipos").prop("selectedIndex");
                $.get("agendaServlet?accion=crearContacto&nombre=" + nom + "&numero=" + nro+ "&tipo=" + pos, function (data) {
                    
                });
            }
            function salir(){
                
                $.get("partidaServlet?accion=salir", function (data) {
                    document.location="/partida/";
                });
            }
         </script>       
         
        <title>Partida</title>
    </head>
    <body>        
        <div><h1>Jugadores: <span id="spanJugadores"></span></h1></div>
        <div><h1>JugadoresMano: <span id="spanJugadoresMano"></span></h1></div>
        <div><h1>Contador: <span id="spanContador"></span></h1></div>
        <%if(estado.equals("apostar")){%> 
            <h1> Error: <%=msg%> </h1>
        <%}%>  
    </body>
</html>

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlador.ControladorLoginJ;
import controlador.VistaInicio;
import controlador.VistaLoginJugador;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.JugadorParticipante;


/**
 *
 * @author FAT
 */
public class VistaWebLogin implements VistaLoginJugador{
    
        ControladorLoginJ controlador;
        private HttpServletRequest request;
        private HttpServletResponse response;
        
        public VistaWebLogin() {
            controlador = new ControladorLoginJ(this);
        }

        @Override
        public void mostrarError(String msg) {
                    try {
                        response.sendRedirect("inicio.jsp?msg=" + msg);
                    } catch (IOException ex) {          
                    }
        }

        @Override
        public void mostrarPartida(JugadorParticipante jp) {
            
                try {
                    request.getSession(true).setAttribute("jugador", jp);
                    response.sendRedirect("Partida.jsp");
                    //System.out.println("Agenda de " + a.getDueño().getNombreCompleto());
                } catch (IOException ex) {            
                }
        }

        public void procesar(HttpServletRequest request, HttpServletResponse response) {
                this.request=request;
                this.response=response;
                String usr = request.getParameter("usuario");
                String pass = request.getParameter("password");
                controlador.login(usr, pass);
            }
        
}

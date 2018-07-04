/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlador.ControladorPartida;
import controlador.VistaPartida;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JButton;
import logica.Apuesta;
import logica.Carta;
import logica.JugadorParticipante;

/**
 *
 * @author FAT
 */
public class VistaWebPartida implements VistaPartida {

    private ControladorPartida controlador;
    private PrintWriter out;
    private AsyncContext contexto;
    private HttpServletRequest request;
    
    public void conectarSSE(HttpServletRequest request) throws IOException {
        
         request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
         contexto = request.startAsync();
         this.request = (HttpServletRequest) contexto.getRequest();
         contexto.getResponse().setContentType("text/event-stream");
         contexto.getResponse().setCharacterEncoding("UTF-8");
         contexto.setTimeout(0);//SIN TIMEOUT
         out = contexto.getResponse().getWriter();
    }
    public void enviar(String evento, String dato) {        
        out.write("event: " + evento + "\n");
        dato = dato.replace("\n", "");
        out.write("data: " + dato + "\n\n");
        if (out.checkError()) {//checkError llama a flush, si da false evio bien
            cerrar();            
        } else {
            //TODO OK!
           //  System.out.println("Enviado");
        }
    }

    private void cerrar() {
       contexto.complete();
    }

    public void inicializar() {        
       JugadorParticipante jp  = (JugadorParticipante)request.getSession(false).getAttribute("jugador");
        controlador = new ControladorPartida(jp, this);        
    }

    public void procesar(String accion, HttpServletRequest request) {
        switch(accion){
            case "salir" : controlador.removerJugador();break;
        }
    }
    
    @Override
    public void mostrarCarta(Carta carta, JButton btn) {
        enviar("mostrarCarta", carta.getCodImagen());
    }

    @Override
    public void mostrarJugadores(ArrayList<JugadorParticipante> jugadores) {
        enviar("listaJugadores", ComponentesHtml.lista(true, "lstJugadores", jugadores));
    }

    @Override
    public void mostrarJugadoresMano(ArrayList<JugadorParticipante> jugadores) {
        enviar("listaJugadoresMano", ComponentesHtml.lista(true, "lstJugadoresMano", jugadores));
    }

    @Override
    public void mostrarValores(int luz, int pozo, int dineroJugador) {
        enviar("mostrarLuz", Integer.toString(luz));
        enviar("mostrarPozo", Integer.toString(pozo));
        enviar("mostrarSaldo", Integer.toString(dineroJugador));
    }

    @Override
    public void mostrarEstado(String estado) {
    }

    @Override
    public void mostrarApuesta(Apuesta a) {
        String htmlApuesta = a.getApostador().getNombre() + " apostó $" + 
                a.getMontoApostado() + ". ¿Aceptas?" + "<br> "
                + "<input type='button' value='Aceptar apuesta' onclick='aceptaApuesta()'>" + 
                "<br><input type='button' value='Aceptar apuesta' onclick='pasaApuesta()'>";
        enviar("recibirApuesta", htmlApuesta);
    }

    @Override
    public void mostrarGanador(JugadorParticipante j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarMano(ArrayList<Carta> cartas) {
        //enviar("mostrarMano", ComponentesHtml.lista(true, "mano", cartas));
    }

    @Override
    public void mostrarNombreJugador(String nombreCompleto) {
        enviar("nombreJugador",nombreCompleto);
    }

    @Override
    public void mostrarGanadorPorSerUltimo(JugadorParticipante ganador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarMensaje(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cambiaDinero(JugadorParticipante jugador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inicioRonda(JugadorParticipante jugador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void esconderAndMostrarAlInicio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iniciarPartida(JugadorParticipante j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void todosPasan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finMano() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aceptarApuesta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarPozo(int pozo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jugadorAposto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jugadorNoPuedeSeguir(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarVentana() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iniciaContador(String str) {
        enviar("contador", str);
    }

    @Override
    public void finalizarContador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarContador(String str) {
        enviar("contador", str);
    }
    
}

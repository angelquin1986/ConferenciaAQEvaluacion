package com.ec.aqsoft.conferencia;

import com.ec.aqsoft.conferencia.pojo.Conferencia;
import com.ec.aqsoft.conferencia.pojo.DatoDoble;
import com.ec.aqsoft.conferencia.pojo.Evento;
import com.ec.aqsoft.conferencia.servicio.ConferenciaServicio;

import java.util.Calendar;
import java.util.List;

/**
 * Clase principal para la ejeccion de la app
 *
 * @author aquingaluisa
 */
public class Main {

    public static void main(String args[]) {
        ConferenciaServicio conferenciaServicio = new ConferenciaServicio();
        DatoDoble<List<Evento>, Conferencia> conferenciaResultado = conferenciaServicio.programarEventos();

        /**
         * Mostrar los datos preliminares
         */
        System.out.println("*****************DATA INICIAL*********************");
        conferenciaResultado.getFirstDate().forEach(x -> {
            System.out.println(x.getId() + " " + x.getNombre() + " " + x.getTiempo());
        });
        /**
         * Mostrar la data final
         */
        System.out.println("*****************HORARIO CONFENCIAS*********************");
        conferenciaResultado.getSecondDate().getTrackList().forEach(x -> {
            System.out.println("Track " + x.getTrackId());
            x.getEspacioTiempoList().forEach(espacio -> {
                espacio.getEventoList().forEach(evento -> {
                    System.out.println(
                            evento.getHoraInicio().get(Calendar.HOUR_OF_DAY) + ":" + evento.getHoraInicio().get(Calendar.MINUTE) + "h "
                                    + evento.getNombre() + "-" + evento.getTiempo());
                });

            });
            System.out.println("**************************************");
        });

    }
}

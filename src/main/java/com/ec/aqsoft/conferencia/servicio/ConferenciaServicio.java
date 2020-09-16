package com.ec.aqsoft.conferencia.servicio;

import com.ec.aqsoft.conferencia.pojo.*;
import com.ec.aqsoft.conferencia.util.ConferenciaConstants;
import com.ec.aqsoft.conferencia.util.ConferenciaUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Logica de negocioa para armar la com.ec.aqsoft.conferencia
 */
public class ConferenciaServicio {

    /**
     * Constructor
     */
    public ConferenciaServicio() {
    }

    /**
     * Metodo principal para programar eventos   y divudirlos por dias a travez de  un archivo como entrada
     */
    public DatoDoble<List<Evento>, Conferencia> programarEventos() {
        //leer el archivo
        List<Evento> eventoListFile = this.leerConferenciasPendientes();
        // programar los eventos
        return new DatoDoble<List<Evento>, Conferencia>(new ArrayList(eventoListFile), this.programarEventos(eventoListFile));
    }

    /**
     * Lee y arma los datos de la com.ec.aqsoft.conferencia y devuelve una structura
     *
     * @return
     */
    private List<Evento> leerConferenciasPendientes() {
        List<Evento> conferenciaList = new ArrayList<>();
        List<String> archivoLineaList = ConferenciaUtil.getInstance().
                leerArchivoConferencia(ConferenciaConstants.NOMBRE_ARCHIVO_CONFERENCIA_CONSTANTES);
        int contador = 1;
        for (String linea : archivoLineaList) {
            Evento evento = new Evento();
            evento.setId(contador++);
            evento.setNombre(linea.substring(0, linea.lastIndexOf(" ")));
            evento.setTiempo(ConferenciaUtil.getInstance()
                    .devolverTiempoEvento(linea.substring(linea.lastIndexOf(" ") + 1)));
            conferenciaList.add(evento);
        }
        return conferenciaList;
    }

    /**
     * Progama los eventos y devuelve la com.ec.aqsoft.conferencia con horario en los eventos
     *
     * @param eventoList
     * @return
     */
    private Conferencia programarEventos(List<Evento> eventoList) {
        Conferencia conferencia = new Conferencia();
        conferencia.setTrackList(new ArrayList<>());
        int trackContador = 0;
        //recorrer mientras exista un evento
        while (0 != eventoList.size()) {
            //duracion de la mañana
            EspacioTiempo espacioTiempoManiana = new EspacioTiempo(
                    ConferenciaConstants.TIEMPO_DURACION_MANIANA, ConferenciaUtil.getInstance().getCalendarHoras(ConferenciaConstants.HORA_INICIO_CONFERENCIA_MANIANA));
            this.asignarEventoAEspacioTiempo(espacioTiempoManiana, eventoList);

            //duracion de la tarde
            EspacioTiempo espacioTiempoAlmuerzo = new EspacioTiempo(
                    ConferenciaConstants.TIEMPO_DURACION_ALMUERZO, ConferenciaUtil.getInstance().getCalendarHoras(ConferenciaConstants.HORA_ALMUERZO));
            this.crearEvento(espacioTiempoAlmuerzo, new Evento(ConferenciaUtil.getInstance().getCalendarHoras(ConferenciaConstants.HORA_ALMUERZO),
                    "ALMUERZO", ConferenciaConstants.TIEMPO_DURACION_ALMUERZO));

            //duracion de la tarde
            EspacioTiempo espacioTiempoTarde = new EspacioTiempo(
                    ConferenciaConstants.TIEMPO_DURACION_TARDE, ConferenciaUtil.getInstance().getCalendarHoras(ConferenciaConstants.HORA_INICIO_CONFERENCIA_TARDE));
            this.asignarEventoAEspacioTiempo(espacioTiempoTarde, eventoList);

            //duracion de nerworking
            EspacioTiempo espacioTiempoNetWorking = new EspacioTiempo(
                    ConferenciaConstants.TIEMPO_DURACION_NETWORKING, ConferenciaUtil.getInstance().getCalendarHoras(ConferenciaConstants.HORA_INICIO_NETWORKING));
            this.crearEvento(espacioTiempoNetWorking, new Evento(ConferenciaUtil.getInstance().getCalendarHoras(ConferenciaConstants.HORA_INICIO_NETWORKING),
                    "NETWORKING", ConferenciaConstants.TIEMPO_DURACION_NETWORKING));

            Track track = new Track();
            track.setTrackId(++trackContador);
            track.setEspacioTiempoList(new ArrayList<>());
            track.getEspacioTiempoList().add(espacioTiempoManiana);
            track.getEspacioTiempoList().add(espacioTiempoAlmuerzo);
            track.getEspacioTiempoList().add(espacioTiempoTarde);
            track.getEspacioTiempoList().add(espacioTiempoNetWorking);

            conferencia.getTrackList().add(track);
        }
        return conferencia;
    }

    /**
     * asigna un evento a un espacion de trabajo y  disminuye tiempo en el espacio
     *
     * @param evento
     */
    private void crearEvento(EspacioTiempo espacioTiempo, Evento evento) {
        if (espacioTiempo.getEventoList() == null) {
            espacioTiempo.setEventoList(new ArrayList<>());
        }
        espacioTiempo.getEventoList().add(evento);
        espacioTiempo.setTiempoRestante(espacioTiempo.getTiempoRestante() - evento.getTiempo());
    }

    /**
     * Asignar un evento  a un espacio tiempo y calcular el  tiempo libre del espacio tiempo
     *
     * @param espacioTiempo
     * @param eventoList
     */
    private void asignarEventoAEspacioTiempo(EspacioTiempo espacioTiempo, List<Evento> eventoList) {
        // initialize the slot start time.
        Calendar horaInicioEspacioTiempo = espacioTiempo.getHoraInicio();
        for (Iterator<Evento> iterator = eventoList.iterator(); iterator.hasNext(); ) {
            Evento evento = iterator.next();

            if (espacioTiempo.getTiempoRestante() >= evento.getTiempo()) {
                // Crear nuevo evento y asignar en espacioTiempo
                this.crearEvento(espacioTiempo, new Evento(horaInicioEspacioTiempo, evento.getNombre(), evento.getTiempo()));
                //calcula la nueva hora de inicio en el tiempo espacio
                horaInicioEspacioTiempo = ConferenciaUtil.getInstance().getNuevaHoraDespejada(horaInicioEspacioTiempo, evento.getTiempo());
                // elimino el evento(Si asingacion) que se asigno ya al espacio timepo , de mi lista
                iterator.remove();
            }
        }
    }
}

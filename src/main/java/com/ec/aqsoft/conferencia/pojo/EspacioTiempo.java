package com.ec.aqsoft.conferencia.pojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author aquingaluisa
 */
public class EspacioTiempo {

    private List<Evento> eventoList;
    private Integer tiempoRestante;
    private Calendar horaInicio;

    public EspacioTiempo(Integer tiempoRestante, Calendar horaInicio) {
        this.eventoList = new ArrayList<>();
        this.tiempoRestante = tiempoRestante;
        this.horaInicio = horaInicio;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public Integer getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(Integer tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }
}

package com.ec.aqsoft.conferencia.pojo;

import java.util.Calendar;

/**
 * Clase que identica una com.ec.aqsoft.conferencia  objeto
 *
 * @author aquingaluisa
 */
public class Evento {
    private Integer id;
    private String nombre;
    private Integer tiempo;
    private Calendar horaInicio;

    /**
     * constructor
     */
    public Evento() {
    }

    /**
     * constructor
     *
     * @param id
     * @param nombre
     * @param tiempo
     */
    public Evento(Integer id, String nombre, Integer tiempo) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    /**
     * Constructor
     *
     * @param horaInicio
     * @param nombre
     * @param tiempo
     */
    public Evento(Calendar horaInicio, String nombre, Integer tiempo) {
        this.horaInicio = horaInicio;
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }
}

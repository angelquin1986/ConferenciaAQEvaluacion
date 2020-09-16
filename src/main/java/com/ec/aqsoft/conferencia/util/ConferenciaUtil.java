package com.ec.aqsoft.conferencia.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Utilitarios para la app com.ec.aqsoft.conferencia
 */
public class ConferenciaUtil {

    private static final ConferenciaUtil INSTANCE = new ConferenciaUtil();

    /**
     * obtener la instancia de la clase
     *
     * @return
     */
    public static ConferenciaUtil getInstance() {
        return INSTANCE;
    }

    /**
     * Leer el archivo de conferencias
     *
     * @param nombreArchivoConferencia
     * @return
     */
    public List<String> leerArchivoConferencia(String nombreArchivoConferencia) {
        FileInputStream fileInputStream = null;
        List<String> lineaConferenciaArchivo = new ArrayList<>();
        try {
            //abrir el archivo para poder leer
            fileInputStream = new FileInputStream(nombreArchivoConferencia);
            //crear un fbuffer para lectura
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String lineaLectura = null;
            //leer miestras exita lineas en el file
            while ((lineaLectura = bufferedReader.readLine()) != null) {
                if (lineaLectura.isEmpty()) {
                    continue;
                }
                lineaConferenciaArchivo.add(lineaLectura);
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lineaConferenciaArchivo;
    }

    /**
     * Retorna el tiempo que dura el vento a partir de un string
     *
     * @param tiempoParametro
     * @return
     */
    public Integer devolverTiempoEvento(String tiempoParametro) {
        Integer tiempoMinutos = 0;
        String tiempo = tiempoParametro.substring(tiempoParametro.lastIndexOf(" ") + 1);
        if (tiempo.equals(ConferenciaConstants.REUNION_RELAMPAGO)) {
            tiempoMinutos = 5;
        } else {
            //dejar solo numeros
            tiempoMinutos = Integer.parseInt(tiempoParametro.replaceAll("\\D+", ""));
        }
        return tiempoMinutos;
    }

    /**
     * retor hora reloj formato Calendarios
     *
     * @param horas
     * @return
     */
    public Calendar getCalendarHoras(Integer horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, horas);
        calendar.set(Calendar.MINUTE, 0);
        return calendar;
    }

    /**
     * retorna la la hora desde que la conferecia tiene libre  el horario
     *
     * @param horalActual
     * @param tiempoEnMinutos
     * @return
     */
    public Calendar getNuevaHoraDespejada(Calendar horalActual, Integer tiempoEnMinutos) {
        Calendar nuevaHoraLibre = Calendar.getInstance();
        nuevaHoraLibre.set(Calendar.HOUR_OF_DAY, horalActual.get(Calendar.HOUR_OF_DAY));
        nuevaHoraLibre.set(Calendar.MINUTE, horalActual.get(Calendar.MINUTE));
        nuevaHoraLibre.add(Calendar.MINUTE, tiempoEnMinutos);
        return nuevaHoraLibre;
    }
}

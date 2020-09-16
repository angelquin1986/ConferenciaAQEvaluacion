package com.ec.aqsoft.conferencia.pojo;

import java.util.List;

/**
 * Clase base para las reunion por dia
 *
 * @author aqiuingaluisa
 */
public class Track {
    private List<EspacioTiempo> espacioTiempoList;
    private Integer trackId;

    public List<EspacioTiempo> getEspacioTiempoList() {
        return espacioTiempoList;
    }

    public void setEspacioTiempoList(List<EspacioTiempo> espacioTiempoList) {
        this.espacioTiempoList = espacioTiempoList;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }
}

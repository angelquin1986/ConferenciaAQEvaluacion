package com.ec.aqsoft.conferencia.pojo;

import java.util.List;

/**
 * Objeto principal com.ec.aqsoft.conferencia
 *
 * @author aquingaluisa
 */
public class Conferencia {
    private List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}

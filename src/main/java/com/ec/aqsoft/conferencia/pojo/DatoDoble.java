package com.ec.aqsoft.conferencia.pojo;

/**
 * Clase que permiste retornnar dos Datos
 */
public class DatoDoble<T, D> {
    private T firstDate;
    private D SecondDate;

    /**
     * Constructor
     *
     * @param firstDate
     * @param secondDate
     */
    public DatoDoble(T firstDate, D secondDate) {
        this.firstDate = firstDate;
        SecondDate = secondDate;
    }

    public T getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(T firstDate) {
        this.firstDate = firstDate;
    }

    public D getSecondDate() {
        return SecondDate;
    }

    public void setSecondDate(D secondDate) {
        SecondDate = secondDate;
    }
}

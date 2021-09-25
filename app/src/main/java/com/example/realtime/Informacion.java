package com.example.realtime;

public class Informacion {

    private String C1,C2,Fecha,ID;

    public Informacion() {

    }

    public Informacion(String c1, String c2, String fecha, String ID) {
        C1 = c1;
        C2 = c2;
        Fecha = fecha;
        this.ID = ID;
    }

    public String getC1() {
        return C1;
    }

    public void setC1(String c1) {
        C1 = c1;
    }

    public String getC2() {
        return C2;
    }

    public void setC2(String c2) {
        C2 = c2;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}

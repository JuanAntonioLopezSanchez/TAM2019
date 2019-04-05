package com.example.a33laboratoriowbjson_sinahiruiz;

class NumerosA {
    String nombre;
    String control;
    String calificacion1;
    String calificacion2;
    String calificacion3;

    public NumerosA(String nombre, String control, String calificacion1, String calificacion2, String calificacion3) {
        this.nombre = nombre;
        this.control = control;
        this.calificacion1 = calificacion1;
        this.calificacion2 = calificacion2;
        this.calificacion3 = calificacion3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getCalificacion1() {
        return calificacion1;
    }

    public void setCalificacion1(String calificacion1) {
        this.calificacion1 = calificacion1;
    }

    public String getCalificacion2() {
        return calificacion2;
    }

    public void setCalificacion2(String calificacion2) {
        this.calificacion2 = calificacion2;
    }

    public String getCalificacion3() {
        return calificacion3;
    }

    public void setCalificacion3(String calificacion3) {
        this.calificacion3 = calificacion3;
    }
}


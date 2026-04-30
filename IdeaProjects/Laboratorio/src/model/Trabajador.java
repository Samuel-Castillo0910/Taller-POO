package model;

import java.util.Objects;

public class Trabajador {
    private String id;
    private String nombre;
    private double desempeño;

    public Trabajador(String id, String nombre, double desempeño) {
        this.id = id;
        this.nombre = nombre;
        this.desempeño = desempeño;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDesempeño() {
        return desempeño;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trabajador that = (Trabajador) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
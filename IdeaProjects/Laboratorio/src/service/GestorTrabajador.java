package service;

import model.Trabajador;
import util.OrdenadorGenerico;
import java.util.*;

public class GestorTrabajador {
    private Set<Trabajador> trabajadores = new HashSet<>();

    public void agregarTrabajador(Trabajador t) {
        trabajadores.add(t);
    }

    public List<Trabajador> obtenerRankingDesempeño() {
        List<Trabajador> lista = new ArrayList<>(trabajadores);
        return OrdenadorGenerico.ordenar(lista,
                Comparator.comparingDouble(Trabajador::getDesempeño).reversed());
    }

    public List<Trabajador> listarPorNombre() {
        return OrdenadorGenerico.ordenar(new ArrayList<>(trabajadores),
                Comparator.comparing(Trabajador::getNombre));
    }
}
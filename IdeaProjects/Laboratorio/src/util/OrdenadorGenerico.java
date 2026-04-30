package util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenadorGenerico {
    public static <T> List<T> ordenar(List<T> lista, Comparator<T> comparador) {
        return lista.stream()
                .sorted(comparador)
                .collect(Collectors.toList());
    }
}
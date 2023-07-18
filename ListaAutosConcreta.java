package ProyectoFinal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAutosConcreta extends ListaAutos {
    public ListaAutosConcreta() {
        autos = new ArrayList<>();
        // Agregamos algunos autos de ejemplo
        autos.add("Nissan Versa");
        autos.add("Kia Rio Sedán");
        autos.add("Chevrolet Aveo");
        autos.add("Nissan March");
        autos.add("MG 5");
        autos.add("Nissan Sentra");
        autos.add("Nissan Versa");
        autos.add("Suzuki Swift");
        autos.add("KIA Rio Hatchback");
    }

    @Override
    public List<String> getAutos() {
        return new ArrayList<>(autos); // Devolvemos una copia de la lista de autos
    }

    @Override
    public void agregarAuto(String nuevoAuto) {
        autos.add(nuevoAuto);
    }

    // Método para restablecer la lista original de autos
    public void restablecerListaOriginal() {
        autos.clear(); // Borramos todos los elementos de la lista
        // Agregamos nuevamente algunos autos de ejemplo
        autos.add("Nissan Versa");
        autos.add("Kia Rio Sedán");
        autos.add("Chevrolet Aveo");
        autos.add("Nissan March");
        autos.add("MG 5");
        autos.add("Nissan Sentra");
        autos.add("Nissan Versa");
        autos.add("Suzuki Swift");
        autos.add("KIA Rio Hatchback");
    }
}
package ProyectoFinal;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class CaracteristicasDialog extends JDialog {  // se genera clase con la extencion JDialog (Esta clase Dialog sirve para  mostrar ventaas de dialogo las cuales no permiten interactura con otras ventanas hasta que se cierren
/*
 * private: Es un modificador de acceso que indica que la constante solo puede ser accedida dentro de la misma clase.
 * 
 * static: Indica que la constante es compartida por todas las instancias de la clase y se asocia con la clase en sí en lugar de con una instancia particular.
 * 
 * final: Indica que la constante no puede ser modificada después de su inicialización y su valor no puede ser cambiado.
 * 
 */
    private static final String[] AUTOS = { //se declara constante de tipo SString
        "Nissan Versa",
        "Kia Rio Sedán",
        "Chevrolet Aveo",
        "Nissan March",
        "Chevrolet Onix",
        "MG 5",
        "Nissan Sentra",
        "Suzuki Swift",
        "KIA Rio Hatchback",
    };
    
    /*Cuando una variable o constante se declara como static, significa que esa variable o 
     * constante pertenece a la clase en sí, en lugar de pertenecer a una instancia particular de esa 
     * clase. Esto significa que todas las instancias de la clase comparten la misma variable o
     * constante static
     * En el caso de una constante static, como AUTOS, todas las instancias de la clase 
     * comparten el mismo arreglo de autos. Esto significa que no importa cuántas instancias de la 
     * clase se creen, todas tendrán acceso al mismo arreglo AUTOS y su contenido será el mismo
     * en todas ellas.
     */

    private static final String[] IMAGENES = { //se genera constante imagenes
        "Nissan Versa.png",
        "Kia Rio Sedán.png",
        "Chevrolet Aveo.png",
        "Nissan March.png",
        "Chevrolet Onix.png",
        "MG 5.png",
        "Nissan Sentra.png",
        "Suzuki Swift.png",
        "KIA Rio Hatchback.png"
    };

    private static final String[][] CARACTERISTICAS = {  			//se genera constante caracteristicas  
        {"Precio en MXN $105800", "Color Rojo", "4 puertas"},
        {"Precio en MXN $205600", "Color Blanco", "4 puertas"},
        {"Precio en MXN $208900", "Color Azul", "4 puertas"},
        {"Precio en MXN $250900", "Color Rojo", "4 puertas"},
        {"Precio en MXN $180900", "Color Rojo", "4 puertas"},
        {"Precio en MXN $200550", "Color Gris", "4 puertas"},
        {"Precio en MXN $300600", "Color Blanco", "4 puertas"},
        {"Precio en MXN $270400", "Color Rojo", "2 puertas"},
        {"Precio en MXN $125800", "Color Azul", "4 puertas"}
    };

    private ImageIcon[] imagenesAutos;  // se genera una variable de tipo ImageIcon para mostrar imagenes 
    private String[] caracteristicas;	//se geneera un arreglo caracteristicas 

    public CaracteristicasDialog(String nombreAuto) {  // se geenra un constructor que hará una sola accion con parametros String
        setTitle("Características de " + nombreAuto); // titulo se establece como el nombre del Auto
        setModal(true); 
        setBounds(150, 150, 600, 300); // poscicion y tamaño del panel de las caracteristicas del carro

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        int indiceAuto = obtenerIndiceAuto(nombreAuto);

        if (indiceAuto >= 0) {
            caracteristicas = CARACTERISTICAS[indiceAuto];

            JLabel labelImagen = new JLabel();
            cargarImagen(nombreAuto, labelImagen);

            JList<String> listaCaracteristicas = new JList<>(caracteristicas);
            listaCaracteristicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, labelImagen, listaCaracteristicas);
            splitPane.setResizeWeight(0.3);

            panel.add(splitPane, BorderLayout.CENTER);
        } else {
            JLabel etiquetaNoEncontrado = new JLabel("No se encontraron características para " + nombreAuto);
            panel.add(etiquetaNoEncontrado, BorderLayout.CENTER);
        }

        setContentPane(panel);
    }

    private void cargarImagen(String nombreAuto, JLabel labelImagen) {
        int indiceAuto = obtenerIndiceAuto(nombreAuto);
        if (indiceAuto >= 0) {
            String rutaImagen = "/Imagenes/" + IMAGENES[indiceAuto];
            ImageIcon imagen = new ImageIcon(getClass().getResource(rutaImagen));
            labelImagen.setIcon(imagen);
        }
    }

    private int obtenerIndiceAuto(String nombreAuto) {
        for (int i = 0; i < AUTOS.length; i++) {
            if (AUTOS[i].equals(nombreAuto)) {
                return i;
            }
        }
        return -1;
    }
}
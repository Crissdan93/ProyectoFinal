package ProyectoFinal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProyectoFinal extends JFrame {

    private ListaAutos listaAutos; // Utilizamos la clase abstracta como tipo
    private ListaDesplegada listaDesplegada; // Agregamos una variable para la lista desplegada

    public ProyectoFinal() {
        setTitle("Lista de autos");
        setBounds(50, 120, 500, 1000);
        listaAutos = new ListaAutosConcreta(); // Instanciamos la subclase concreta

        FondoPanel fondoPanel = new FondoPanel();
        setContentPane(fondoPanel);
        fondoPanel.setLayout(null);

        JButton boton1 = new JButton();
        boton1.setText("ABRIR LISTA");
        boton1.setBounds(150, 470, 150, 30);
        fondoPanel.add(boton1);

        JButton botonAgregar = new JButton(); // Botón para agregar nuevos autos
        botonAgregar.setText("AGREGAR AUTO");
        botonAgregar.setBounds(150, 510, 150, 30);
        fondoPanel.add(botonAgregar);

        listaDesplegada = new ListaDesplegada(); // Inicializamos la lista desplegada
        listaDesplegada.restablecerListaCompleta(); // Modificar aquí para llamar al método correcto
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listaDesplegada.setVisible(true);
            }
        });

        listaDesplegada.agregarSeleccionador(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String nombreAutoSeleccionado = listaDesplegada.getAutoSeleccionado();
                    CaracteristicasDialog dialogo = new CaracteristicasDialog(nombreAutoSeleccionado);
                    dialogo.setVisible(true);
                }
            }
        });

        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nuevoAuto = JOptionPane.showInputDialog(ProyectoFinal.this, "Ingrese el nombre del nuevo auto:", "Agregar Auto", JOptionPane.PLAIN_MESSAGE);
                if (nuevoAuto != null && !nuevoAuto.isEmpty()) {
                    listaAutos.agregarAuto(nuevoAuto); // Agregamos el nuevo auto a la lista abstracta
                    listaDesplegada.agregarAuto(nuevoAuto); // Agregamos el nuevo auto a la lista desplegada
                    System.out.println("Nuevo auto agregado: " + nuevoAuto);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProyectoFinal ventana = new ProyectoFinal();
                ventana.setVisible(true);
            }
        });
    }
}

class FondoPanel extends JPanel {
    private Image imagen;

    public FondoPanel() {
        imagen = new ImageIcon(getClass().getResource("/Imagenes/autos.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}        	
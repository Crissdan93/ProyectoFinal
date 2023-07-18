package ProyectoFinal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDesplegada extends JFrame {
    private JPanel panelPrincipal;
    private JList<String> listaAutos;
    private JTextField campoBusqueda;
    private JButton botonBuscar;
    private DefaultListModel<String> modeloAutos;
    private List<String> listaAutosOriginal; // Agregamos una lista para almacenar la lista original de autos

    public ListaDesplegada() {
        setTitle("Lista de autos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 300, 300);

        String[] autos = {
            "Nissan Versa",
            "Kia Rio Sedán",
            "Chevrolet Aveo",
            "Nissan March",
            "Chevrolet Onix",
            "MG 5",
            "Nissan Sentra",
            "Suzuki Swift",
            "KIA Rio Hatchback"
        };

        listaAutosOriginal = new ArrayList<>(Arrays.asList(autos)); // Almacenamos la lista original de autos
        modeloAutos = new DefaultListModel<>(); // Inicializamos el modelo de la lista

        for (String auto : autos) {
            modeloAutos.addElement(auto); // Agregamos los autos al modelo
        }

        listaAutos = new JList<>(modeloAutos); // Usamos el modelo para la JList
        JScrollPane scrollPane = new JScrollPane(listaAutos);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelPrincipal.setLayout(new BorderLayout(0, 0));

        campoBusqueda = new JTextField();
        botonBuscar = new JButton("Buscar");

        JPanel panelBusqueda = new JPanel(new BorderLayout());
        panelBusqueda.add(campoBusqueda, BorderLayout.CENTER);
        panelBusqueda.add(botonBuscar, BorderLayout.EAST);

        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String palabraClave = campoBusqueda.getText().trim();
                if (!palabraClave.isEmpty()) {
                    buscarAutosCoincidentes(palabraClave);
                } else {
                    restablecerListaCompleta(); // Restablecer la lista original si el campo de búsqueda está vacío
                }
            }
        });

        campoBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarAutosCoincidentes(campoBusqueda.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String palabraClave = campoBusqueda.getText().trim();
                if (palabraClave.isEmpty()) {
                    restablecerListaCompleta(); // Restablecer la lista original si se borra el contenido del campo de búsqueda
                } else {
                    buscarAutosCoincidentes(palabraClave);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No necesitamos hacer nada aquí para el caso de documentos con atributos.
            }
        });
    }

    public void buscarAutosCoincidentes(String palabraClave) {
        // Restablecer la lista original si el campo de búsqueda está vacío
        if (palabraClave.isEmpty()) {
            restablecerListaCompleta();
            return;
        }

        ListModel<String> modeloOriginal = listaAutos.getModel();
        DefaultListModel<String> modeloFiltrado = new DefaultListModel<>();
        for (int i = 0; i < modeloOriginal.getSize(); i++) {
            String auto = modeloOriginal.getElementAt(i);
            if (auto.toLowerCase().contains(palabraClave.toLowerCase())) {
                modeloFiltrado.addElement(auto);
            }
        }
        listaAutos.setModel(modeloFiltrado);
    }

    public void restablecerListaCompleta() {
        listaAutos.setModel(modeloAutos); // Restablecer la lista original usando el modelo
    }

    public void agregarSeleccionador(ListSelectionListener lista) {
        listaAutos.addListSelectionListener(lista);
    }

    public String getAutoSeleccionado() {
        return listaAutos.getSelectedValue();
    }

    public void agregarAuto(String nuevoAuto) {
        modeloAutos.addElement(nuevoAuto); // Agregar el nuevo auto al modelo
    }
}
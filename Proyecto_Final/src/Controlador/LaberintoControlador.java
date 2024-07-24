package Controlador;

import Modelo.Laberinto;
import Modelo.Celda;
import Vista.LaberintoGui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LaberintoControlador {
    private Laberinto modelo;
    private LaberintoGui vista;

    public LaberintoControlador(Laberinto modelo, LaberintoGui vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.addGenerarLaberintoListener(new GenerarLaberintoListener());
        this.vista.addResolverLaberintoListener(new ResolverLaberintoListener());
    }

    class GenerarLaberintoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vista.generarLaberinto();
        }
    }

    class ResolverLaberintoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[][] matrizLaberinto = modelo.generarMatrizLaberinto(vista.getBotones());
            List<Celda> camino = modelo.findPathRecursive(matrizLaberinto); // Puedes cambiar el método a findPathWithCache, findPathBFS, o findPathDFS según sea necesario

            if (camino != null) {
                for (Celda celda : camino) {
                    vista.colorearCelda(celda.row, celda.col, Color.GREEN);
                }
                vista.mostrarResultado("Camino encontrado");
            } else {
                vista.mostrarResultado("No hay camino posible");
            }
        }
    }
}

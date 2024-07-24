package Controlador;

import Modelo.Laberinto;
import Modelo.Celda;
import Vista.LaberintoGui;

import java.awt.*;
import javax.swing.*;
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
            boolean[][] matrizLaberinto = new boolean[vista.getBotones().length][vista.getBotones()[0].length];
            JButton[][] botones = vista.getBotones();
            
            for (int i = 0; i < botones.length; i++) {
                for (int j = 0; j < botones[i].length; j++) {
                    matrizLaberinto[i][j] = botones[i][j].getBackground() == Color.WHITE;
                }
            }
            
            int inicioX, inicioY, finX, finY;
            try {
                inicioX = vista.getInicioX();
                inicioY = vista.getInicioY();
                finX = vista.getFinX();
                finY = vista.getFinY();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese números válidos para las coordenadas de inicio y fin.");
                return;
            }

            long startTime = System.currentTimeMillis();
            List<Celda> camino = modelo.findPathDFS(matrizLaberinto); // Puedes cambiar a otros métodos de búsqueda
            long endTime = System.currentTimeMillis();

            if (camino != null) {
                for (Celda celda : camino) {
                    vista.colorearCelda(celda.row, celda.col, Color.GREEN);
                }
                vista.mostrarResultado("Camino encontrado");
            } else {
                vista.mostrarResultado("No hay camino posible");
            }

            vista.txtTiempo.setText((endTime - startTime) + " ms");
        }
    }
}

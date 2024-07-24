import Controlador.LaberintoControlador;
import Modelo.Laberinto;
import Vista.LaberintoGui;

public class App {
    public static void main(String[] args) {
        Laberinto modelo = new Laberinto();
        LaberintoGui vista = new LaberintoGui();
        LaberintoControlador controlador = new LaberintoControlador(modelo, vista);

        vista.setVisible(true);
        // boolean[][] grid = {
        //     {true, true, false, true},S
        //     {true, false, true, true},
        //     {true, true, true, false},
        //     {false, true, true, true}
        // };
        // int inicioX = 0, inicioY = 0;
        // int finX = 3, finY = 3;

        // LaberintoControlador controlador = new LaberintoControlador(grid, inicioX, inicioY, finX, finY);
        // controlador.ejecutarMetodos();



    }
}

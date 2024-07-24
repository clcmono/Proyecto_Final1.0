import Modelo.Laberinto;
import Vista.LaberintoGui;
import Controlador.LaberintoControlador;

public class App {
    public static void main(String[] args) {
        Laberinto modelo = new Laberinto();
        LaberintoGui vista = new LaberintoGui();
        LaberintoControlador controlador = new LaberintoControlador(modelo, vista);

        vista.setVisible(true);
    }
}

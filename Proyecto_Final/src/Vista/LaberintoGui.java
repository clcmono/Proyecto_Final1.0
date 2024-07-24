package Vista;
import Modelo.Laberinto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LaberintoGui extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtAltura;
    private JTextField txtAnchura;
    private JTextField txtInicioX;
    private JTextField txtInicioY;
    private JTextField txtFinX;
    private JTextField txtFinY;
    private JTextField txtResultado;
    public JTextField txtTiempo;
    private JPanel laberintoPanel;
    private JButton[][] botones;
    private int altura;
    private int anchura;
    private boolean[][] grid;
    private Laberinto laberinto;
    private JButton btnGenerar;
    private JButton btnResolver;

    public LaberintoGui() {
        setTitle("Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Titulo
        JLabel lblTitulo = new JLabel("Laberinto");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel que recibe los inputs
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblAltura = new JLabel("Alto:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(lblAltura, gbc);

        txtAltura = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(txtAltura, gbc);
        txtAltura.setColumns(5);

        JLabel lblAnchura = new JLabel("Ancho:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        topPanel.add(lblAnchura, gbc);

        txtAnchura = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 0;
        topPanel.add(txtAnchura, gbc);
        txtAnchura.setColumns(5);

        btnGenerar = new JButton("Generar Laberinto");
        gbc.gridx = 4;
        gbc.gridy = 0;
        topPanel.add(btnGenerar, gbc);

        JLabel lblInicioX = new JLabel("Inicio X:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(lblInicioX, gbc);

        txtInicioX = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        topPanel.add(txtInicioX, gbc);
        txtInicioX.setColumns(5);

        JLabel lblInicioY = new JLabel("Inicio Y:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        topPanel.add(lblInicioY, gbc);

        txtInicioY = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 1;
        topPanel.add(txtInicioY, gbc);
        txtInicioY.setColumns(5);

        JLabel lblFinX = new JLabel("Fin X:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(lblFinX, gbc);

        txtFinX = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        topPanel.add(txtFinX, gbc);
        txtFinX.setColumns(5);

        JLabel lblFinY = new JLabel("Fin Y:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        topPanel.add(lblFinY, gbc);

        txtFinY = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 2;
        topPanel.add(txtFinY, gbc);
        txtFinY.setColumns(5);

        btnResolver = new JButton("Resolver Laberinto");
        gbc.gridx = 4;
        gbc.gridy = 2;
        topPanel.add(btnResolver, gbc);

        contentPane.add(topPanel, BorderLayout.NORTH);

        // Panel donde se genera el laberinto
        laberintoPanel = new JPanel();
        laberintoPanel.setLayout(new GridLayout());
        contentPane.add(laberintoPanel, BorderLayout.CENTER);

        // Panel para resultados
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblResultado = new JLabel("Resultado:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(lblResultado, gbc);

        txtResultado = new JTextField();
        txtResultado.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        bottomPanel.add(txtResultado, gbc);

        JLabel lblTiempo = new JLabel("Tiempo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        bottomPanel.add(lblTiempo, gbc);

        txtTiempo = new JTextField();
        txtTiempo.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        bottomPanel.add(txtTiempo, gbc);

        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addGenerarLaberintoListener(ActionListener listener) {
        btnGenerar.addActionListener(listener);
    }

    public void addResolverLaberintoListener(ActionListener listener) {
        btnResolver.addActionListener(listener);
    }

    public int getAltura() throws NumberFormatException {
        return Integer.parseInt(txtAltura.getText());
    }

    public int getAnchura() throws NumberFormatException {
        return Integer.parseInt(txtAnchura.getText());
    }

    public int getInicioX() throws NumberFormatException {
        return Integer.parseInt(txtInicioX.getText());
    }

    public int getInicioY() throws NumberFormatException {
        return Integer.parseInt(txtInicioY.getText());
    }

    public int getFinX() throws NumberFormatException {
        return Integer.parseInt(txtFinX.getText());
    }

    public int getFinY() throws NumberFormatException {
        return Integer.parseInt(txtFinY.getText());
    }

    public JButton[][] getBotones() {
        return botones;
    }

    public void generarLaberinto() {
        try {
            altura = getAltura();
            anchura = getAnchura();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para el alto y ancho.");
            return;
        }

        laberintoPanel.removeAll();
        laberintoPanel.setLayout(new GridLayout(altura, anchura));
        botones = new JButton[altura][anchura];
        grid = new boolean[altura][anchura];

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(Color.WHITE);
                botones[i][j].addActionListener(e -> {
                    JButton boton = (JButton) e.getSource();
                    if (boton.getBackground() == Color.WHITE) {
                        boton.setBackground(Color.RED);
                    } else {
                        boton.setBackground(Color.WHITE);
                    }
                });
                laberintoPanel.add(botones[i][j]);
                grid[i][j] = true;
            }
        }

        laberintoPanel.revalidate();
        laberintoPanel.repaint();
    }

    public void mostrarResultado(String resultado) {
        txtResultado.setText(resultado);
    }

    public void colorearCelda(int row, int col, Color color) {
        botones[row][col].setBackground(color);
    }
}

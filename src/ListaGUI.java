import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ListaGUI {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Utilizable lista=new ListaHerramienta();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame=new JFrame();
        JPanel ventana=new JPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(2,2,10,10));
        ventana.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Map<JButton, ActionListener> botones=new HashMap<>();
        botones.put(new JButton("Mostrar"), e -> JOptionPane.showMessageDialog(null,lista.muestraTodos()));
        botones.put(new JButton("Añadir"),e->lista.pideYAnyade());
        botones.put(new JButton("Leer de Fichero"),e->leer(lista));
        botones.put(new JButton("Escribir en Fichero"),e->escribir(lista));
        for(Map.Entry<JButton,ActionListener> boton:
            botones.entrySet()){
            boton.getKey().setMargin(new Insets(20, 20, 20, 20));
            boton.getKey().addActionListener(boton.getValue());
            ventana.add(boton.getKey());
        }
        frame.add(ventana);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void escribir(Utilizable lista) {
        JFileChooser pedirFichero=new JFileChooser();
        pedirFichero.showSaveDialog(null);
        lista.guardaEnFichero(pedirFichero.getSelectedFile());
    }

    private static void leer(Utilizable lista) {
        JFileChooser pedirFichero=new JFileChooser();
        pedirFichero.showOpenDialog(null);
        lista.leeDeFichero(pedirFichero.getSelectedFile());
    }
}

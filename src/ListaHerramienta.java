import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListaHerramienta implements Utilizable{
    public static final char DELIMITADOR = ';';
    List<Herramienta> lista = new ArrayList<>();
    @Override
    public String muestraTodos() {
        return lista.stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public void leeDeFichero(File fichero) {
        String nombreHerramienta;
        int precioHerramienta;
        int posicionDelimitador;
        try {
            List<String> lineasFichero = Files.readAllLines(fichero.toPath());
            for (int i = 0; i < lineasFichero.size(); i++) {
                posicionDelimitador = lineasFichero.get(i).indexOf(DELIMITADOR);
                nombreHerramienta = lineasFichero.get(i).substring(0, posicionDelimitador);
                precioHerramienta = Integer.parseInt(lineasFichero.get(i).substring(posicionDelimitador + 1));
                lista.add(new Herramienta(nombreHerramienta, precioHerramienta));
            }
        }catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,"Introduzca un precio válido");
            }
        catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        catch (StringIndexOutOfBoundsException siobe){
            JOptionPane.showMessageDialog(null,"Error en la lectura del fichero, puede que el delimitador no sea el correcto (;)");
        }
    }

    @Override
    public void guardaEnFichero(File fichero) {
        try (PrintWriter out = new PrintWriter(fichero)) {
            out.println(lista.stream().map(Objects::toString).collect(Collectors.joining("\n")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pideYAnyade(){

        PanelPrincipal panel = new PanelPrincipal();

    }

    class Ventana extends JFrame {
        Ventana(){
            setLayout(new GridLayout(0,1));
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(250,250);
            setLocationRelativeTo(null);
            setResizable(false);
        }
    }

    class Panel extends JPanel {
        private JLabel nombreHerramienta = new JLabel("Nombre de la herramienta: ");
        private JFormattedTextField nombreH = new JFormattedTextField();
        private JLabel precioHerramienta = new JLabel("Precio de la Herramienta: ");
        private JFormattedTextField precioH = new JFormattedTextField();
        private JPanel panelEtiquetas = new JPanel();

        Panel(){
            panelEtiquetas.setLayout(new GridLayout(0,1));
            panelEtiquetas.add(nombreHerramienta);
            panelEtiquetas.add(nombreH);
            panelEtiquetas.add(precioHerramienta);
            panelEtiquetas.add(precioH);
            setLayout(new BorderLayout());
            add(panelEtiquetas,BorderLayout.CENTER);
        }

        public JFormattedTextField getNombreH(){
            return this.nombreH;
        }
        public JFormattedTextField getPrecioH(){
            return this.precioH;
        }

    }

    class PanelPrincipal {
        private Ventana ventana = new Ventana();
        private Panel panel = new Panel();
        private JButton crear = new JButton("Añadir herramienta");
        PanelPrincipal(){
            ventana.setLayout(new BorderLayout());
            ventana.add(panel,BorderLayout.CENTER);
            ventana.add(crear,BorderLayout.SOUTH);
            ventana.pack();
            ventana.setVisible(true);

            crear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try{
                        String nombreHerramienta = panel.getNombreH().getText();
                        double precioHerramienta = Double.parseDouble(panel.getPrecioH().getText());
                        lista.add(new Herramienta(nombreHerramienta,precioHerramienta));
                        JOptionPane.showMessageDialog(null,"La herramienta "+nombreHerramienta+" con el precio "+precioHerramienta+" € ha sido añadida corretamente. ");
                        ventana.dispose();
                    }catch (NullPointerException npe){
                        JOptionPane.showMessageDialog(null,npe.getMessage());
                    }
                    catch (NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null,"Introduce un precio válido.");
                    }
                }
            });
        }
    }

}

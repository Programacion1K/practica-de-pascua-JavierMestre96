import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListaHerramienta implements Utilizable{
    List<Herramienta> lista = new ArrayList<>();
    @Override
    public String muestraTodos() {
        return lista.stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public void leeDeFichero(File fichero) {
        String nombreHerramienta = "";
        double precioHerramienta = 0;
        int posicionDelimitador = 0;
        try{
            List<String> lineasFichero = Files.readAllLines(fichero.toPath());
            for (int i = 0; i<lineasFichero.size();i++){
                posicionDelimitador = lineasFichero.get(i).indexOf(':');
                nombreHerramienta = lineasFichero.get(i).substring(0,posicionDelimitador);
                precioHerramienta = Double.parseDouble(lineasFichero.get(i).substring(posicionDelimitador));
                lista.add(new Herramienta(nombreHerramienta,precioHerramienta));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void guardaEnFichero(File fichero) {

    }

    @Override
    public void pideYAnyade() {

    }


}

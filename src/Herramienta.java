public class Herramienta {
    private final String nombre;
    private final double precio;

    Herramienta(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre(){
        return this.nombre;
    }
    public double getPrecio(){
        return this.precio;
    }

    public String toString(){
        return this.nombre+':'+this.precio;
    }
}

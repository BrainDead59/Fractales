package fractales;
/**
 * En esta clase planteamos nuestra Excepcion en el caso de que en los calculos del Multibrot se salga del rango de la imagen.
 */
public class NuestraExcepcion extends Exception{
    private String error;
    
    NuestraExcepcion(String msg){
        super(msg);
        error="valores de 'x' y 'y' fuera del rango, debido a la potencia";
    }
    
    @Override
    public String toString(){
        return "Nuestra Excepcion: ["+" error "+"]";
    }
}

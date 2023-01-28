package fractales;

public class NuestraExcepcion2 extends Exception{
    private String error;
    
    NuestraExcepcion2(String msg){
        super(msg);
        error="el valor de la potencia es muy grande";
        System.out.println(error);
    }
    
    @Override
    public String toString(){
        return "Nuestra Excepcion 2 : ["+" error "+"]";
    }
}
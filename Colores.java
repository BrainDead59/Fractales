package fractales;

/**
 * Esta clase permite asignar la paleta de colores a los fractales de Julia y Mandelbrot, en base al numero de iteraciones que tenga el fractal. El valor
 * de los colores que tendra se da por medio del corriminto de los bits y el uso del RGB como medio de composicion para los colores.
 */
public class Colores{
    private int maxIt;
    
    public Colores(int maxIt){
        this.maxIt = maxIt;
    }
    
    /**
     * Mediante este metodo podemos devolver a la clase que utiliza el correspondiente objeto color, el color que se haya seleccionado, y en la imagen
     * correspondiente del fractal se podran ver los colores .
     * @param i
     * @param c
     * @return 
     */
    public int getColor(int i, int c) {
        int a = (int) (255 * ((double) i) / (maxIt / 4));
        
        if(c==1)
            return ( (2*a<<16) );
        else if(c==2)
            return ( (255 * (i/15)) << 16 | (255 * (i/15)) );
        else if(c==3)
            return ((255 * (i/20)) << 16 | 0 | 0 );
        else if(c==4)
            return (65536 + i*256 + i/2+128);
        else if(c==5)
            return ( (0) | (2*a<<17) | (a<<4) | ((a*3)<<0) );     
        return((255 * (i/10)) << 16 | (255 * (i/10)) << 8 | (255 * (i/10)) );
        
    }
}
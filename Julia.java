package fractales;

import java.awt.Color; 
import java.io.File; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Mediante la clase Julia, se realiza de forma paralela la realizacion del fractal de Julia el cual desciende del fractal de Mandelbrot, pero al ser una
 * implementacion paralela del algoritmo, se decidio en no heredad directo de Mandelbrot debido a que se tendría que realizar de nuevo toda la implementa
 * cion, y no sería eficiente heredar. 
 * 
 * En el caso de la implementacion con los hilos se herada de la clase que permite esta implementacion que es Thread y con los metodos que se heradan se
 * puede implementar cada uno de los metodos para poder realizar el fractal de Julia.
 */
public class Julia extends Thread{
  final static int ancho = 1920;
  final static int largo = 1080;
  final static int max = 300;
  private int  zoom=1, id, col, nimg;
  final static BufferedImage imagen = new BufferedImage(ancho, largo, BufferedImage.TYPE_INT_RGB);

  /**
   * Metodo que permite la implementacion de la paralelizacion del fractal mediante los hilos de Java, se manejan cuatro hilos, y cada uno se encarga
   * de una porcion de la imagen final que se crea en el metodo run.
   * @throws ArithmeticException
   * @throws InterruptedException 
     * @throws NuestraExcepcion 
   */
  public void julia()throws ArithmeticException, InterruptedException{
    int negro = 0;

    int[] colors = new int[max];

    for (int i = 0; i<max; i++) {
      colors[i] = Color.HSBtoRGB(i/256f, 1, i/(i+8f));
    }
    Julia thread0 = new Julia(0, col);
    Julia thread1 = new Julia(1, col);
    Julia thread2 = new Julia(2, col);
    Julia thread3 = new Julia(3, col);
    
    thread0.start();
    thread1.start();
    thread2.start();
    thread3.start();
    
    thread0.join();
    thread1.join();
    thread2.join();
    thread3.join();

    try{
      ImageIO.write(imagen, "png", new File("Julia"+nimg+".png"));
    }catch(IllegalArgumentException iae){
       System.out.println("Alguno de los parámetros establecidos para generar la imagen no existe");
    }catch(IOException e){
      System.out.println("Error 38");    
    }
    System.out.println("Fractal Terminado, revise la carpeta donde almacenó estos programas");
   }
  public Julia(){}
  public Julia(int id, int col) {
    this.col=col;
    this.id = id;
   } 
  
  /**
   * Setter de los atributos del numero de imagen y del color que selecciona el usuario.
   * @param nimg 
   */
  public void setImg(int nimg){
      this.nimg=nimg;
  }
  public void setColor(int col){
      this.col=col;
  }

  /**
   * Metodo run que es una sobreesritura del metodo run() que se hereda de Threads, con este se puede definir el inicion de cada uno de los hilos, para
   * que cada uno pueda trabajar en una porcionde la imagen que representa el fractal.
   * 
   * Posteriormente se realiza el calculo de los valores reales e imaginarios que permiten determinar si el valor converge o diverge, para cada uno de los
   * dos casos se otorga un color.
   */
  @Override
  public void run(){
    int begin=0, end=0;
    if (id == 0) {
        begin = 0;
        end = (ancho / 4) * 1;
    }
    else if (id == 1) {
        begin = (ancho/ 4) * 1;
        end = (ancho/ 4) * 2;
    }
    else if (id == 2) {
        begin = (ancho/ 4) * 2;
        end = (ancho/ 4) * 3;
    } 
    else if (id == 3) { 
        begin = (ancho/ 4) * 3;
        end = ancho;
    }


    for (int x = begin; x < end; x++) {
       Colores color = new Colores(max);
      for (int y = 0; y < largo; y++) {
        double zx = 1.5 * (x - ancho / 2) / (0.5 * zoom * ancho) ;
        double zy = (y - largo / 2) / (0.5 * zoom * largo);
        float i = max;
        while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy -0.7;
                    zy = 2.0 * zx * zy + 0.27015;
                    zx = tmp;
                    i--;
                }
        int c=0;
                if(col==7)
                    c=Color.HSBtoRGB((max/ i) % 1, 1, i > 0 ? 1 : 0);
                else
                    c = color.getColor((int)i, col);
                imagen.setRGB(x, y, c);
            }
     }
  }    
 }


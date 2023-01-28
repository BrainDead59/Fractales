package fractales;

import java.awt.geom.Point2D;
import java.io.File; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Por medio de esta clase se puede realizar la implementacion del Multibrot que es una variante del Mandelbrot que se caracteriza por que se colocan 
 * varios fractales de Mandelbrot dentro de la misma imagen ademas que estos forman un tipo espejo ya que se mantiene la estructura caracteristica del 
 * Mandelbrot.
 */
public class Multibrot{
  final static int ancho = 1080;
  final static int alto = 1080;
  final static int max = 100;
  private int  col, nimg;
  public static Point2D centro = new Point2D.Double(-0.5, 0);
  public static double tamano = 1.25;
  public static int radioAn = ancho / alto;
  private static double pot;
  public static int pq = 16;
  
  final static BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

  /**
   * En este caso la implementacion no es paralela por la dependencia de los valores, por que en esta variante se deben considerar donde se termina la 
   * anterior y para implementar el paralelismo se debe de encontrar un elemento que mejorar, situacion que no se presenta en esta implementacion, ya que
   * el usar hilos haria mas lento el procesamiento.
   * @throws ArithmeticException 
   */
  public void multibrot()throws ArithmeticException{
    try{
        int negro = 0;
        for (int i = 0; i < ancho; i++) {
                            for (int j = 0; j < alto; j++) {
                                    double a = map(i, 0, ancho, centro.getX() - tamano * radioAn, centro.getX() + tamano * radioAn);
                                    double b = map(j, 0, alto, centro.getY() - tamano, centro.getY() + tamano);
                                    
                                    double ca = a;
                                    double cb = b;

                                    int n = 0;
                                    while (n <= max) {
                                            double na = Math.pow(Math.sqrt(a * a + b * b), pot) * Math.cos(pot * Math.atan2(b, a));
                                            double nb = Math.pow(Math.sqrt(a * a + b * b), pot) * Math.sin(pot * Math.atan2(b, a));
                                            if (Math.sqrt(a * a + b * b) > pq)
                                                    break;
                                            a = na + ca;
                                            b = nb + cb;

                                            n++;
                                    }

                                    int color;
                                    if (n == max) {
                                            color = negro;
                                    } else {
                                            color = (int) map(n, 0, max, 0, 255);
                                    }
                                    imagen.setRGB(i, j, color);
                            }
                         }
    }catch(NuestraExcepcion e){
        System.out.println("Excepcion atrapada " + e);
    }
    try{
      ImageIO.write(imagen, "png", new File("Multibrot"+nimg+".png"));
    }catch(IllegalArgumentException iae){
      System.out.println("Alguno de los parámetros establecidos para generar la imagen no existe");
    }
    catch(IOException e){
      System.out.println("Error 38");    
    }
    System.out.println("Fractal Terminado, revise la carpeta donde almacenó estos programas");
  }
  
  
  public void setPot(int pot){
      this.pot=pot;
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
  public static double map(double value, double istart, double istop, double ostart, double ostop) throws NuestraExcepcion{
      if(ostart + (ostop - ostart) * ((value - istart) / (istop - istart))>ancho){
          throw new NuestraExcepcion("Linea 91");
      }
      return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
  }
    
 }
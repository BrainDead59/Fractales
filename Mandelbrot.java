package fractales;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * La clase permite la implementacion del fractal de Mandelbrot el cual se caracteriza por ser un implementaciona paralela del algoritmo, por esto se hereda
 * de Thread para poder implementar los hilos, de esta forma se le asigna a cada uno de los hilos que porcion de la imagen debe de realizar.
 */
public class Mandelbrot extends Thread {
final static int N = 4096;
final static int IT = 100;
private int id, c;
private int cimg;
static int[][] set = new int[N][N];

/**
   * Metodo que permite la implementacion de la paralelizacion del fractal mediante los hilos de Java, se manejan cuatro hilos, y cada uno se encarga
   * de una porcion de la imagen final que se crea en el metodo run.
   * @throws InterruptedException 
   */
public void mandelbrot() throws InterruptedException {

    Mandelbrot thread0 = new Mandelbrot(0);
    Mandelbrot thread1 = new Mandelbrot(1);
    Mandelbrot thread2 = new Mandelbrot(2);
    Mandelbrot thread3 = new Mandelbrot(3);
    thread0.start();
    thread1.start();
    thread2.start();
    thread3.start();

    thread0.join();
    thread1.join();
    thread2.join();
    thread3.join();

    BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_RGB);
    File archivo = new File("FractalMandelbrot"+cimg+".png");
    Colores color = new Colores(IT);

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {

            int k = set[i][j];

            int pixel;
            if (k < IT) {
                  pixel = color.getColor(k, c);
            } else {
                pixel = 0;
            }
            
            img.setRGB(i, j, pixel);
        }
    }
    try {
                ImageIO.write(img, "png", archivo);
            }
            catch(IllegalArgumentException iae){
                System.out.println("Alguno de los parámetros establecidos para generar la imagen no existe");
            }
            catch (IOException e) {
                System.out.println("Error al crear la imagen");
            }
    System.out.println("Fractal Terminado, revise la carpeta donde almacenó estos programas");

}
public Mandelbrot(){}

public Mandelbrot(int id) {
    this.id = id;
}

/**
   * Setter de los atributos del numero de imagen y del color que selecciona el usuario.
   * @param nimg 
   */
public void setColor(int c){
    this.c=c;
}
public void setImg(int cimg){
    this.cimg=cimg;

}

/**
   * Metodo run que es una sobreesritura del metodo run() que se hereda de Threads, con este se puede definir el inicio de cada uno de los hilos, para
   * que cada uno pueda trabajar en una porcionde la imagen que representa el fractal.
   * 
   * Posteriormente se realiza el calculo de los valores reales e imaginarios que permiten determinar si el valor converge o diverge, para cada uno de los
   * dos casos se otorga un color.
   */
@Override
public void run() {
    
     int begin = 0, end = 0;

    if (id == 0) {
        begin = 0;
        end = (N / 4) * 1;
    }
    else if (id == 1) {
        begin = (N / 4) * 1;
        end = (N / 4) * 2;
    }
    else if (id == 2) {
        begin = (N / 4) * 2;
        end = (N / 4) * 3;
    } 
    else if (id == 3) { 
        begin = (N / 4) * 3;
        end = N;
    }

    for (int i = begin; i < end; i++) {
        for (int j = 0; j < N; j++) {

            double cr = (4.0 * i - 2 * N) / N;
            double ci = (4.0 * j - 2 * N) / N;

            double zr = cr, zi = ci;

            int k = 0;
            while (k < IT && zr * zr + zi * zi < 4.0) {
                double newr = cr + zr * zr - zi * zi;
                double newi = ci + 2 * zr * zi;

                zr = newr;
                zi = newi;

                k++;
            }

            set[i][j] = k;
        }
    }
}

}
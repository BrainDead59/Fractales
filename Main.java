package fractales;

import java.awt.Frame;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @autores:
 * 
 * La clase permite el manejo de los objetos de las clases Julia, Mandelbrot y Multibrot, mediante esta clase principal es posible manipular los colores
 * de que pueden tener los fractales de Mandelbrot y de Julia, y para la clase Multibrot seleccionar una potencia.
 */
public class Main {
    static Frame frame;
    
    /**
     * El metodo permite la seleccion del fractal que se desea ver Julia, Mandelbrot y Multibrot. Para el caso de los dos primeros se puede seleccionar una
     * gama de distintos coloes, y para el ultimo se puede seleccionar la potencia del fractal.
     * @param args
     * @throws InputMismatchException 
     */
    public static void main(String args[]) throws InputMismatchException, NuestraExcepcion2{
        Scanner sc=new Scanner(System.in);
        int op=0, opc2=0, contimg=1;
        do{
           try{
            System.out.println("\t****Proyecto Generador de fractales*****\nEscoja el fractal que quiere hacer\n"
                    + "1)Conjunto de Mandelbrot\n2)Julia\n3)Multibrot con potencia a elegir\n4)Salir");
            op=sc.nextInt();
            if(op==1){
                do{
                System.out.println("¿Color del conjunto de Mandelbrot?\n1)Rojo y Negro Obscuro\n2)Rosa y Negro\n3)Rojo y Negro encendido\n"
                        + "4)Azul, Verde y Negro\n5)Colores fríos\n6)Blanco y Negro\n7)Salir");
                opc2=sc.nextInt();
                Mandelbrot m = new Mandelbrot();
                    switch (opc2) {
                        case 1:
                            m.setColor(1);
                            m.setImg(contimg);
                            m.mandelbrot();
                            contimg++;
                            break;
                        case 2:
                            m.setColor(2);
                            m.setImg(contimg);
                            m.mandelbrot();
                            contimg++;
                            break;
                        case 3:
                            m.setColor(3);
                            m.setImg(contimg);
                            m.mandelbrot();
                            contimg++;
                            break;
                        case 4:
                            m.setColor(4);
                            m.setImg(contimg);
                            m.mandelbrot();
                            contimg++;
                            break;
                        case 5:
                            m.setColor(5);
                            m.setImg(contimg);
                            m.mandelbrot();
                            contimg++;
                            break;
                        case 6:
                            m.setColor(6);
                            m.setImg(contimg);
                            m.mandelbrot();
                            contimg++;
                            break;
                        default:
                            break;
                    }
                }while(opc2>0&&opc2<=6);
            }
            if(op==2){
                do{
                    System.out.println("¿Color del fractal de Julia?\n1)Rojo y Negro Obscuro\n2)Rosa y Negro\n3)Rojo y Negro encendido\n"
                        + "4)Azul, Verde y Negro\n5)Colores fríos\n6)Blanco y Negro\n7)Multicolor\n8)Salir");
                opc2=sc.nextInt();
                Julia j = new Julia();
                    switch (opc2) {
                        case 1:
                            j.setColor(1);
                            j.setImg(contimg);
                            j.julia();
                            contimg++;
                            break;
                        case 2:
                            j.setColor(2);
                            j.setImg(contimg);
                            j.julia();
                            contimg++;
                            break;
                        case 3:
                            j.setColor(3);
                            j.setImg(contimg);
                            j.julia();
                            contimg++;
                            break;
                        case 4:
                            j.setColor(4);
                            j.setImg(contimg);
                            j.julia();
                            contimg++;
                            break;
                        case 5:
                            j.setColor(5);
                            j.setImg(contimg);
                            j.julia();
                            contimg++;
                            break;
                        case 6:
                            j.setColor(6);
                            j.setImg(contimg);
                            j.julia();
                            contimg++;
                            break;
                        case 7:
                            j.setColor(7);
                            j.setImg(contimg);
                            j.julia();
                            contimg++;
                            break;
                        default:
                            break;
                    }
                }while(opc2>0&&opc2<=7);
            }
            if(op==3){
                Multibrot mb = new Multibrot();
                System.out.println("Potencia del Multibrot??\tTrate de escoger potencias de 1 a 4");
                int om = sc.nextInt();
                if(om>10){
                    throw new NuestraExcepcion2("Potencia muy grande");}
                else{
                mb.setPot(om);
                mb.setImg(contimg);
                System.out.println("Calculando Multibrot, esto podrá tomar un tiempo debido al tamaño de la potencia");
                mb.multibrot();
                contimg++;}
              }
           }
           catch(NuestraExcepcion2 ne2){
               System.out.println("Excepcion Atrapada " + ne2);
           }
           catch(InterruptedException ie){
               System.out.println("Algun Hilo se vio interrumpido abruptamente");
               ie.printStackTrace();
               break;
           }
           catch(ArithmeticException ae){
               System.out.println("No se puedo generar el fractal, debido a una división entre cero que pudo ocurrir");
               ae.printStackTrace();
               break;
           }
           catch(InputMismatchException ime){
               System.out.println("El valor ingresado es un caracter");
               ime.printStackTrace();
               break;
           }
        }while(op>0&&op<=3);
    }

}
